package varadraj.order.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.common.model.JsonResponse;
import varadraj.order.model.OrderCreationRequest;
import varadraj.order.model.Orders;
import varadraj.order.service.OrderService;

@RestController
@CrossOrigin
@PreAuthorize(value = "hasRole('USER')")
@RequestMapping("/auth/customer/order")
public class OrderCustomerController {
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/getAll")
	public JsonResponse getCustomerOrders(Principal user) {
		
		return new JsonResponse(200, orderService.getAllCustomerOrders(user.getName()));
	}
	
	@PostMapping("/addOrder")
	public JsonResponse addOrder(@RequestBody OrderCreationRequest request,Principal user) {
		
		Orders newOrder = orderService.addOrder(request, user.getName());
		
		if(newOrder == null) {
			return new JsonResponse(400, "Invalid Order");}
		else {
			return new JsonResponse(201,"Order Created");}
	}

}
