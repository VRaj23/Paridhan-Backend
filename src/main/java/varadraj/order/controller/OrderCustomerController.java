package varadraj.order.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.common.model.JsonResponse;
import varadraj.order.model.OrderCreationRequest;
import varadraj.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderCustomerController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/customerOrder")
	public JsonResponse getCustomerOrders(Principal user) {
		
		return new JsonResponse(501, "Not implemented");
	}
	
	@PostMapping("/addOrder")
	public JsonResponse addOrder(@RequestBody OrderCreationRequest request) {
		if(!orderService.isValidRequest(request))
			return new JsonResponse(400, "Invalid Order");
		
		orderService.addOrder(request);
		return new JsonResponse(201,"Order Created");
	}
	
	

}
