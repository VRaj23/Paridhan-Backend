package varadraj.product.service;

import java.io.IOException;
import java.util.function.UnaryOperator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import graphql.GraphQL;
import graphql.schema.idl.TypeRuntimeWiring.Builder;
import varadraj.common.util.GraphQLService;
import varadraj.product.service.datafetcher.AllProductsDataFetcher;
import varadraj.product.service.datafetcher.ProductDataFetcher;

@Service
public class ProductGraphQLService extends GraphQLService{
	
	private static final String graphQLSchemaFileName = "product.graphql";

	public ProductGraphQLService() {
		super(graphQLSchemaFileName);
	}
	
	@Autowired
	private AllProductsDataFetcher allProductsDataFetcher;
	
	@Autowired
	private ProductDataFetcher productDataFetcher;

	@Override
	public GraphQL getGraphQL() throws IOException {
		return this.loadSchema();
	}

	@Override
	protected UnaryOperator<Builder> getTypeWiringBuilder() {
		return typeWiring -> typeWiring
				.dataFetcher("allProducts", allProductsDataFetcher)
				.dataFetcher("product", productDataFetcher);
	}
	
}