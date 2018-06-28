package varadraj.order.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionResult;
import varadraj.common.model.GraphQLRequest;
import varadraj.common.model.GraphQLResponse;
import varadraj.common.model.ResponseMessage;
import varadraj.order.service.CustomerOrderGraphQLService;
import varadraj.order.service.OrderGraphQLService;

@RestController
@RequestMapping("/auth/order/graphql")
public class OrderGraphQLController {
	
	@Autowired
	private OrderGraphQLService orderGraphQL;
	
	@Autowired
	private CustomerOrderGraphQLService customerOrderGrahpQL;

	@PostMapping
	public GraphQLResponse orderGraphQL(@RequestBody GraphQLRequest request, Principal user){
		String username = user.getName();
		String query = request.getQuery();
		String variables = request.getVariables().toString();
		System.out.println("variables "+variables);//TODO remove
		ExecutionResult result;
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean isAdmin = authentication.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		boolean isUser = authentication.getAuthorities().stream()
				.anyMatch(r -> r.getAuthority().equals("ROLE_USER"));
		
		try {
			if(isAdmin) {
				result = this.orderGraphQL.getGraphQL().execute(query);
				return new GraphQLResponse(result);
			}else if(isUser) {
				result = this.customerOrderGrahpQL.getGraphQL(username, variables).execute(query);
				return new GraphQLResponse(result);
			}else {
				return new GraphQLResponse(401, ResponseMessage.UNAUTHORIZED,null);
			}
		}catch(IOException e) {
			return new GraphQLResponse(500, ResponseMessage.ERROR, "Unable to read Schema File");
		}	
	}
}