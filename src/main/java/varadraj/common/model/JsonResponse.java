package varadraj.common.model;

public class JsonResponse<T> {
	
	private int status;
	private JsonResponseMessage message;
	private T response;
	
	
	public JsonResponse() {}


	public JsonResponse(int status, JsonResponseMessage message, T response) {
		super();
		this.status = status;
		this.message = message;
		this.response = response;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public JsonResponseMessage getMessage() {
		return message;
	}


	public void setMessage(JsonResponseMessage message) {
		this.message = message;
	}


	public T getResponse() {
		return response;
	}


	public void setResponse(T response) {
		this.response = response;
	}


	
}
