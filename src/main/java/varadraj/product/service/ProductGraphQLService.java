package varadraj.product.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import varadraj.product.service.datafetcher.AllProductsDataFetcher;
import varadraj.product.service.datafetcher.ProductDataFetcher;

@Service
public class ProductGraphQLService {
	
	@Value("classpath:product.graphql")
	private Resource resource;
	
	private GraphQL graphQL;
	
	@Autowired
	private AllProductsDataFetcher allProductsDataFetcher;
	
	@Autowired
	private ProductDataFetcher productDataFetcher;
	
	public GraphQL getGraphQL() {
		return this.graphQL;
		
	}
	
	@PostConstruct
	private void loadSchema() throws IOException {
		File schemaFile = resource.getFile();
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = this.buildRunTimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		this.graphQL = GraphQL.newGraphQL(schema).build();
	}

	private RuntimeWiring buildRunTimeWiring() {
		return RuntimeWiring.newRuntimeWiring()
				.type("Query", typeWiring -> typeWiring
						.dataFetcher("allProducts", allProductsDataFetcher)
						.dataFetcher("product", productDataFetcher)
					 )				
				.build();
	}

}
