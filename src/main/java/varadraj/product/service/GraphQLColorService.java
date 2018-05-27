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
import varadraj.product.service.datafetcher.AllColorDataFetcher;
import varadraj.product.service.datafetcher.ColorDataFetcher;

@Service
public class GraphQLColorService {

	@Value("classpath:color.graphql")
	private Resource resource;
	
	@Autowired
	private AllColorDataFetcher allColorDataFetcher;
	
	@Autowired
	private ColorDataFetcher colorDataFetcher;
	
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
						.dataFetcher("allColors", allColorDataFetcher)
						.dataFetcher("color", colorDataFetcher)
					)
				.build();
	}
}
