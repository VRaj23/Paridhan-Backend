package varadraj.product.service;

import java.io.IOException;
import java.util.function.UnaryOperator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import graphql.GraphQL;
import graphql.schema.idl.TypeRuntimeWiring.Builder;
import varadraj.common.util.GraphQLService;
import varadraj.product.service.datafetcher.AllColorDataFetcher;
import varadraj.product.service.datafetcher.ColorDataFetcher;

@Service
public class ColorGraphQLService extends GraphQLService{
	
	private static final String graphQLSchemaFileName = "color.graphql";

	public ColorGraphQLService() {
		super(graphQLSchemaFileName);
	}

	@Autowired
	private AllColorDataFetcher allColorDataFetcher;
	
	@Autowired
	private ColorDataFetcher colorDataFetcher;
	
	@Override
	public GraphQL getGraphQL() throws IOException {
		return this.loadSchema();
	}
	
	@Override
	protected UnaryOperator<Builder> getTypeWiringBuilder() {
		return typeWiring -> typeWiring
				.dataFetcher("allColors", allColorDataFetcher)
				.dataFetcher("color", colorDataFetcher);
	}
}