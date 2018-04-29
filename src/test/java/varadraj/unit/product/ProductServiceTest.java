package varadraj.unit.product;

import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import varadraj.product.model.ProductCreationRequest;
import varadraj.product.model.ProductHeader;
import varadraj.product.model.ProductType;
import varadraj.product.repository.ProductHeaderRepository;
import varadraj.product.repository.ProductLineRepository;
import varadraj.product.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DataJpaTest
public class ProductServiceTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	ProductHeaderRepository headerRepo;
	
	@Autowired
	ProductLineRepository lineRepo;

	@Test
	public void testIsValidRequest() {
		
		ProductHeader header = new ProductHeader();
		header.setProductType(new ProductType("testProduct"));
		
		ProductHeader savedHeader = entityManager.persist(header);
		
		assertThat(savedHeader.getProductType(), Matchers.is(header.getProductType()));
		
	}

}
