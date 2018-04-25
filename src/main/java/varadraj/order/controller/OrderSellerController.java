package varadraj.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.common.model.JsonResponse;
import varadraj.order.service.OrderService;

@RestController
@RequestMapping("/order/seller")
public class OrderSellerController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public String hello() {
		return "Hello Order Seller";
	}
	
	@GetMapping("/allOrders")
	public JsonResponse getOrders() {
		
		return new JsonResponse(501,"not implemented");
	}
}
