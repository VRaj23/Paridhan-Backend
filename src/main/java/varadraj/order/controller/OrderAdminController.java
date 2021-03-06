package varadraj.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.common.model.JsonResponse;
import varadraj.common.model.ResponseMessage;
import varadraj.order.model.OrderResponse;
import varadraj.order.service.OrderService;

@RestController
@CrossOrigin
@PreAuthorize(value = "hasRole('ADMIN')")
@RequestMapping("/auth/admin/order/")
public class OrderAdminController {

	@Autowired
	private OrderService orderService;
	
	
	@GetMapping("/all")
	public JsonResponse<List<OrderResponse>> getOrders() {
		
		return new JsonResponse<List<OrderResponse>>(200
				,ResponseMessage.OK,
				orderService.getAllOrdersAdmin());
	}
}
