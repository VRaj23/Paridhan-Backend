package varadraj.common.util;

import java.util.List;

import graphql.ExecutionResult;
import graphql.GraphQLError;
import varadraj.common.model.JsonResponse;
import varadraj.common.model.JsonResponseMessage;

public class GraphQLResponse {
	
	private JsonResponse<Object> response;
	
	public GraphQLResponse(ExecutionResult result) {
		response = new JsonResponse<Object>();
		this.processResult(result);
	}
	
	public JsonResponse<Object> getJsonResponse(){
		return this.response;
	}
	
	private void processResult(ExecutionResult result) {
		if (result.getData() == null) {
			this.processError(result.getErrors());
		}else {
			this.response.setStatus(200);
			this.response.setMessage(JsonResponseMessage.OK);
			this.response.setResponse(result.getData());
		}
	}

	private void processError(List<GraphQLError> errors) {
		
		this.response.setResponse(errors.get(0).getLocations());
		
		switch(errors.get(0).getErrorType()) {
			case DataFetchingException:
				this.response.setStatus(500);
				this.response.setMessage(JsonResponseMessage.GraphQL_DataFetchingException);
				break;
			case ExecutionAborted:
				this.response.setStatus(500);
				this.response.setMessage(JsonResponseMessage.GraphQL_ExecutionAborted);
				break;
			case InvalidSyntax:
				this.response.setStatus(400);
				this.response.setMessage(JsonResponseMessage.GraphQL_InvalidSyntax);
				break;
			case OperationNotSupported:
				this.response.setStatus(405);
				this.response.setMessage(JsonResponseMessage.GraphQL_OperationNotSupported);
				break;
			case ValidationError:
				this.response.setStatus(400);
				this.processValidationError(errors.get(0));
				break;
			default:
				this.response.setStatus(400);
				this.response.setMessage(JsonResponseMessage.BAD_REQUEST);
				break;
		
		}
		
	}

	private void processValidationError(GraphQLError error) {
		String validationErrorType = error.getMessage();
		if(validationErrorType.startsWith("Validation error of type FieldUndefined:"))
			this.response.setMessage(JsonResponseMessage.GraphQL_FieldUndefined);
		else if(validationErrorType.startsWith("Validation error of type SubSelectionRequired:"))
			this.response.setMessage(JsonResponseMessage.GraphQL_SubSelectionRequired);
		else
			this.response.setMessage(JsonResponseMessage.BAD_REQUEST);
	}

}
