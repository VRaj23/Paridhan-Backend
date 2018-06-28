package varadraj.common.model;

public class GraphQLRequest {

	private Object operationName;
    private Object variables;
    private String query;
    
    public GraphQLRequest() {}
    
	public Object getOperationName() {
		return operationName;
	}
	public void setOperationName(Object operationName) {
		this.operationName = operationName;
	}
	public Object getVariables() {
		return variables;
	}
	public void setVariables(Object variables) {
		this.variables = variables;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}    
	
}
