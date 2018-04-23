package varadraj.product.model;

public class HeaderCreationRequest{
	
	private long productTypeID;
	private double price;
	private double discount;
	private long brandID;
	private long imageID;
	private boolean enabled = true;
	
	public HeaderCreationRequest() {}
	
	public HeaderCreationRequest(long productTypeID, double price, double discount, long brandID, long imageID,
			boolean enabled) {
		super();
		this.productTypeID = productTypeID;
		this.price = price;
		this.discount = discount;
		this.brandID = brandID;
		this.imageID = imageID;
		this.enabled = enabled;
	}

	public long getProductTypeID() {
		return productTypeID;
	}
	public void setProductTypeID(long productTypeID) {
		this.productTypeID = productTypeID;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public long getBrandID() {
		return brandID;
	}
	public void setBrandID(long brandID) {
		this.brandID = brandID;
	}
	public long getImageID() {
		return imageID;
	}
	public void setImageID(long imageID) {
		this.imageID = imageID;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}