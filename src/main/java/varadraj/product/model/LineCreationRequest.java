package varadraj.product.model;

public class LineCreationRequest{
	
	private long headerID;
	private String name;
	private boolean available = true;
	private long colorID;
	private long sizeID;	
	
	public LineCreationRequest() {}

	public LineCreationRequest(long headerID, String name, boolean available, long colorID, long sizeID) {
		super();
		this.headerID = headerID;
		this.name = name;
		this.available = available;
		this.colorID = colorID;
		this.sizeID = sizeID;
	}
	
	public long getHeaderID() {
		return headerID;
	}
	public void setHeaderID(long headerID) {
		this.headerID = headerID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public long getColorID() {
		return colorID;
	}
	public void setColorID(long colorID) {
		this.colorID = colorID;
	}
	public long getSizeID() {
		return sizeID;
	}
	public void setSizeID(long sizeID) {
		this.sizeID = sizeID;
	}	
	
}