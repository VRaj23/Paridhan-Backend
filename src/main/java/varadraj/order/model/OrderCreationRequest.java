package varadraj.order.model;

import java.util.ArrayList;
import java.util.List;

public class OrderCreationRequest {
	
	private OrderHeaderCreationRequest header;
	private List<OrderLineCreationRequest> lines = new ArrayList<>();
	
	public OrderCreationRequest() {}
	
	public OrderCreationRequest(OrderHeaderCreationRequest header, List<OrderLineCreationRequest> lines) {
		this.header = header;
		this.lines = lines;
	}

	public OrderHeaderCreationRequest getHeader() {
		return header;
	}

	public void setHeader(OrderHeaderCreationRequest header) {
		this.header = header;
	}

	public List<OrderLineCreationRequest> getLines() {
		return lines;
	}

	public void setLines(List<OrderLineCreationRequest> lines) {
		this.lines = lines;
	}
	
	

}
