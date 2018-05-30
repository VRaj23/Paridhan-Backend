package varadraj.order.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.common.model.JsonResponse;
import varadraj.common.model.JsonResponseMessage;
import varadraj.common.util.GraphQLResponse;
import varadraj.order.service.OrderGraphQLService;

@RestController
@RequestMapping("/graphql/order")
public class OrderGraphQLController {
	
	@Autowired
	private OrderGraphQLService graphQL;
	
	@PostMapping("/")
	public JsonResponse<Object> orderGraphQL(@RequestBody String query){
		try {
			return new GraphQLResponse( this.graphQL.getGraphQL().execute(query) ).getJsonResponse();
		} catch (IOException e) {
			e.printStackTrace();
			return new JsonResponse<Object>(500, JsonResponseMessage.ERROR, "unable to load Order GraphQL Schema File");
		}
		
	}

}
