package varadraj.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionResult;
import varadraj.product.service.GraphQLColorService;

@RestController
@RequestMapping("/graphql/product/")
public class GraphQLController {
	
	@Autowired
	private GraphQLColorService graphQL;

	@PostMapping("color")
	public ResponseEntity<ExecutionResult> colorGraphQL(@RequestBody String query) {
		ExecutionResult result = graphQL.getGraphQL().execute(query);
		return new ResponseEntity<ExecutionResult>(result, HttpStatus.OK);
	}
}
