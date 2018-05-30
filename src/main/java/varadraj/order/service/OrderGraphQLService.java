package varadraj.order.service;

import java.io.IOException;
import java.util.function.UnaryOperator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import graphql.GraphQL;
import graphql.schema.idl.TypeRuntimeWiring.Builder;
import varadraj.common.util.GraphQLService;
import varadraj.order.service.datafetcher.AllOrderDataFetcher;
import varadraj.order.service.datafetcher.OrderDataFetcher;

@Service
public class OrderGraphQLService extends GraphQLService{

	private static final String graphQLSchemaFileName = "order.graphql";
	
	public OrderGraphQLService() {
		super(graphQLSchemaFileName);
	}
	
	@Autowired
	private AllOrderDataFetcher allOrderDataFetcher;
	
	@Autowired
	private OrderDataFetcher orderDataFetcher;

	@Override
	public GraphQL getGraphQL() throws IOException {
		return this.loadSchema();
	}

	@Override
	protected UnaryOperator<Builder> getTypeWiringBuilder() {
		return typeWiring -> typeWiring
				.dataFetcher("allOrders", allOrderDataFetcher)
				.dataFetcher("order", orderDataFetcher);
	}
	
}