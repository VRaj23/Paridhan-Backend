package varadraj.common.model;

import java.util.List;

import graphql.ExecutionResult;
import graphql.GraphQLError;

public class GraphQLResponse {
	
	private int status;
	private ResponseMessage message;
	private Object data;
	
	public GraphQLResponse() {}
	 
	public GraphQLResponse(int status, ResponseMessage message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public GraphQLResponse(ExecutionResult result) {		
		this.processResult(result);
	}
	
	private void processResult(ExecutionResult result) {
		if (result.getData() == null) {
			this.processError(result.getErrors());
		}else {
			this.status = 200;
			this.message = ResponseMessage.OK;
			this.data = result.getData();
		}
	}

	private void processError(List<GraphQLError> errors) {
		
		this.data = errors.get(0).getLocations();
		
		switch(errors.get(0).getErrorType()) {
			case DataFetchingException:
				this.status = 500;
				this.message = ResponseMessage.GraphQL_DataFetchingException;
				break;
			case ExecutionAborted:
				this.status = 500;
				this.message = ResponseMessage.GraphQL_ExecutionAborted;
				break;
			case InvalidSyntax:
				this.status = 400;
				this.message = ResponseMessage.GraphQL_InvalidSyntax;
				break;
			case OperationNotSupported:
				this.status = 405;
				this.message = ResponseMessage.GraphQL_OperationNotSupported;
				break;
			case ValidationError:
				this.status = 400;
				this.processValidationError(errors.get(0));
				break;
			default:
				this.status = 400;
				this.message = ResponseMessage.BAD_REQUEST;
				break;
		
		}
		
	}

	private void processValidationError(GraphQLError error) {
		String validationErrorType = error.getMessage();
		if(validationErrorType.startsWith("Validation error of type FieldUndefined:"))
			this.message = ResponseMessage.GraphQL_FieldUndefined;
		else if(validationErrorType.startsWith("Validation error of type SubSelectionRequired:"))
			this.message = ResponseMessage.GraphQL_SubSelectionRequired;
		else
			this.message = ResponseMessage.BAD_REQUEST;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ResponseMessage getMessage() {
		return message;
	}

	public void setMessage(ResponseMessage message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}