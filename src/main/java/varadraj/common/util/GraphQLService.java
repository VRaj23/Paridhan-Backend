package varadraj.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.UnaryOperator;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring.Builder;

/**
 * Handles boilerplate code for creation of GraphQL Services
 * GraphQL Service classes will link schema files in src/main/resources with implementations of DataFetcher interface
 * 
 * @author VRaj23
 */

public abstract class GraphQLService {
	
	private String graphQLSchemaFileName;
	
	public GraphQLService(String graphQLSchemaFileName) {
		this.graphQLSchemaFileName = graphQLSchemaFileName;
	}
	
	public abstract GraphQL getGraphQL() throws IOException;
	
	protected abstract UnaryOperator<Builder> getTypeWiringBuilder();
	
	
	
	@PostConstruct
	protected final GraphQL loadSchema() throws IOException {

		InputStream inputStream = this.getClass()
				.getClassLoader()
				.getResourceAsStream(this.graphQLSchemaFileName);
		
		File schemaFile = File.createTempFile(this.graphQLSchemaFileName, ".temp");
		try {
		    FileUtils.copyInputStreamToFile(inputStream, schemaFile);
		}catch(IOException ioE){
			ioE.printStackTrace();
		} finally {
		    IOUtils.closeQuietly(inputStream);
		}
		
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = this.buildRuntimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		return GraphQL.newGraphQL(schema).build();
	}
	
	private RuntimeWiring buildRuntimeWiring() {
		return RuntimeWiring.newRuntimeWiring()
				.type( "Query", this.getTypeWiringBuilder()	).build();
	}	

}
