package varadraj.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionResult;
import varadraj.common.model.JsonResponse;
import varadraj.common.model.JsonResponseMessage;
import varadraj.order.service.OrderServiceGraphQL;

@RestController
@RequestMapping("/graphql/order")
public class GraphQLOrderController {
	
	@Autowired
	private OrderServiceGraphQL graphQL;
	
	@PostMapping("/")
	public JsonResponse<ExecutionResult> orderGraphQL(@RequestBody String query){
		ExecutionResult result = this.graphQL.getGraphQL().execute(query);
		return new JsonResponse<ExecutionResult>(200, JsonResponseMessage.OK, result);
	}

}
