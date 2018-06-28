package varadraj.order.service;

import java.io.IOException;
import java.util.List;
import java.util.function.UnaryOperator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.idl.TypeRuntimeWiring.Builder;
import varadraj.common.util.GraphQLService;
import varadraj.order.model.OrderResponse;

@Service
public class CustomerOrderGraphQLService extends GraphQLService{
	
	private static final String graphQLSchemaFileName = "order.graphql";
	
	private String username;
	private int orderID = -1;
	
	@Autowired
	private OrderService orderService;	

	public CustomerOrderGraphQLService() {
		super(graphQLSchemaFileName);
	}

	@Override
	public GraphQL getGraphQL() throws IOException {
		return this.getGraphQL(null, null);
	}
	
	public GraphQL getGraphQL(String username, String variables) throws IOException {
		this.username = username;
		if(! variables.equals("{}") ) {
			this.orderID = this.getOrderID(variables);
		}
		return this.loadSchema();
	}

	private int getOrderID(String variables) {
		if(variables.startsWith("{orderID=")) {
			String value = variables.substring(variables.indexOf("=")+1, variables.indexOf("}"));
			System.out.println("value "+value);
			return Integer.parseInt(value);
		}
		return -1;
	}

	@Override
	protected UnaryOperator<Builder> getTypeWiringBuilder() {
		return typeWiring -> typeWiring
				.dataFetcher("allOrders", new DataFetcher<List<OrderResponse>>() {
					@Override
					public List<OrderResponse> get(DataFetchingEnvironment environment) {
						return orderService.getAllCustomerOrders(username);
					}
				})
				.dataFetcher("order", new DataFetcher<OrderResponse>() {
					@Override
					public OrderResponse get(DataFetchingEnvironment environment) {
						if(orderID == -1)
							return null;
						else
							return orderService.getCustomerOrder(username, new Long(orderID));
					}
				})
				;
	}

}
