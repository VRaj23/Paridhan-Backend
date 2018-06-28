package varadraj.order.model;

import java.time.LocalDateTime;

import varadraj.common.model.address.AddressResponse;
import varadraj.user.model.customer.CustomerResponse;

public class OrderResponse{
	
    private long orderID;
    private String productName;
    private String colorName;
    private String sizeChar;
    private String brandName;
    private long imageID;
    private String typeName;
    private String status;
    private double amount;
    private int quantity;
    private LocalDateTime creationDateTime;
	private CustomerResponse customerResponse;
	private AddressResponse deliveryAddress;
	
	public OrderResponse() {}

	public OrderResponse(long orderID, String productName, String colorName, String sizeChar, String brandName,
			long imageID, String typeName, String status, double amount, int quantity, LocalDateTime creationDateTime,
			CustomerResponse customerResponse, AddressResponse deliveryAddress) {
		super();
		this.orderID = orderID;
		this.productName = productName;
		this.colorName = colorName;
		this.sizeChar = sizeChar;
		this.brandName = brandName;
		this.imageID = imageID;
		this.typeName = typeName;
		this.status = status;
		this.amount = amount;
		this.quantity = quantity;
		this.creationDateTime = creationDateTime;
		this.customerResponse = customerResponse;
		this.deliveryAddress = deliveryAddress;
	}

	public long getOrderID() {
		return orderID;
	}

	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getSizeChar() {
		return sizeChar;
	}

	public void setSizeChar(String sizeChar) {
		this.sizeChar = sizeChar;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public long getImageID() {
		return imageID;
	}

	public void setImageID(long imageID) {
		this.imageID = imageID;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public CustomerResponse getCustomerResponse() {
		return customerResponse;
	}

	public void setCustomerResponse(CustomerResponse customerResponse) {
		this.customerResponse = customerResponse;
	}

	public AddressResponse getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(AddressResponse deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	
}
