package varadraj.order.service;

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
import varadraj.order.service.datafetcher.AllOrderDataFetcher;

@Service
public class OrderServiceGraphQL {

	@Value("classpath:order.graphql")
	private Resource resource;
	
	@Autowired
	private AllOrderDataFetcher allOrderDataFetcher;
	
	private GraphQL graphQL;
	
	public GraphQL getGraphQL() {
		return this.graphQL;
	}
	
	@PostConstruct
	private void loadSchema() throws IOException {
		File schemaFile = resource.getFile();
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = this.buildRuntimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		this.graphQL = GraphQL.newGraphQL(schema).build();
	}
	
	private RuntimeWiring buildRuntimeWiring() {
		return RuntimeWiring.newRuntimeWiring()
				.type("Query", typeWiring -> typeWiring
						.dataFetcher("allOrders", allOrderDataFetcher)
						//.dataFetcher("order", orderDataFetcher)
					 )
				.build();
	}
}
