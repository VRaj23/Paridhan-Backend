package varadraj.unit.order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import springfox.documentation.spring.web.plugins.DocumentationPluginsBootstrapper;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import varadraj.common.model.address.Address;
import varadraj.common.service.AddressService;
import varadraj.exception.InvalidInputException;
import varadraj.order.model.OrderCreationRequest;
import varadraj.order.model.OrderStatus;
import varadraj.order.model.Orders;
import varadraj.order.repository.OrderRepository;
import varadraj.order.service.OrderService;
import varadraj.product.model.ProductLine;
import varadraj.product.service.ProductService;
import varadraj.user.model.customer.Customer;
import varadraj.user.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class OrderServiceTest {
	
	@MockBean
	DocumentationPluginsBootstrapper mock; //for Swagger2

	@MockBean
	WebMvcRequestHandlerProvider handler;  //for Swagger2
	
	@MockBean
	JavaMailSender emailSender; //for Email Service
	
	@Mock
	private OrderRepository orderRepo;
	
	@Mock
	private AddressService addressService;
	
	@Mock
	private ProductService productService;
	
	@Mock	
	private CustomerService customerService;
	
	@InjectMocks
	private OrderService orderService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addOrderTest_whenValidRequest_thenSave() throws InvalidInputException {
		OrderCreationRequest request = new OrderCreationRequest(
				 100.0 	//amount
				,11 	//addressID
				,22 	//productID
				, 1		//quantity
				);
		
		Customer customer = new Customer();
		customer.setName("cust");
		ProductLine item = new ProductLine();
		item.setLineID(22);
		Address deliveryAddress = new Address();
		deliveryAddress.setAddressID(11);

		Orders order = new Orders(
				 customer			//Customer
				,item 				//ProductLine
				,1 					//quantity
				,100.0 				//amount
				,deliveryAddress	//address
				);
		
		when(customerService.findByCustomerUsername("cust")).thenReturn(Optional.of(customer));
		when(addressService.findByAddressID(11)).thenReturn(Optional.of(deliveryAddress));
		when(productService.findByLineID(22)).thenReturn(item);
		when(orderRepo.save(Mockito.any(Orders.class))).thenReturn(order);
		
		Optional<Orders> savedOrder = orderService.addOrder(Optional.of(request), "cust");
		
		assertTrue(savedOrder.isPresent());
		assertEquals(savedOrder.get().getAmount(), request.getAmount(),0);
		assertEquals(savedOrder.get().getQuantity(), request.getQuantity());
		assertEquals(savedOrder.get().getOrderStatus(), OrderStatus.NEW);
		
		
	}
	
	@Test(expected = InvalidInputException.class)
	public void addOrderTest_whenInvalidQuantity_thenThrowException() throws InvalidInputException {
		OrderCreationRequest request = new OrderCreationRequest(
				 100.0 	//amount
				,11 	//addressID
				,22 	//productID
				, 0		//quantity
				);
		
		orderService.addOrder(Optional.of(request), "cust");
	}

}
