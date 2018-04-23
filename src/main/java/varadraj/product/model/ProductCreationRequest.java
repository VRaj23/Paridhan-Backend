package varadraj.product.model;

import java.util.ArrayList;
import java.util.List;

public class ProductCreationRequest {
	
	private HeaderCreationRequest header;
	private List<LineCreationRequest> lines = new ArrayList<>();
	
	public ProductCreationRequest() {}
	
	public ProductCreationRequest(HeaderCreationRequest header, List<LineCreationRequest> lines) {
		super();
		this.header = header;
		this.lines = lines;
	}
	
	public HeaderCreationRequest getHeader() {
		return header;
	}
	public void setHeader(HeaderCreationRequest header) {
		this.header = header;
	}
	public List<LineCreationRequest> getLines() {
		return lines;
	}
	public void setLines(List<LineCreationRequest> lines) {
		this.lines = lines;
	}	

}