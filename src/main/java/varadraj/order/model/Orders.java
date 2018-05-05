package varadraj.order.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import varadraj.common.model.address.Address;
import varadraj.product.model.ProductLine;
import varadraj.user.model.customer.Customer;

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private long orderID;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_line_id")
	private ProductLine item;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "order_status")
	private OrderStatus orderStatus;
	
	private double amount;
	
	private int quantity = 1;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery_address_id")
	private Address deliveryAddress;
	
	private LocalDateTime creationDateTime;
	
	private LocalDateTime lastUpateDateTime;
	
	public Orders() {}

	public Orders(Customer customer, ProductLine item, double amount, int quantity, Address deliveryAddress) {
		super();
		this.customer = customer;
		this.item = item;
		this.amount = amount;
		this.quantity = quantity;
		this.deliveryAddress = deliveryAddress;
		this.orderStatus = OrderStatus.NEW;
		this.creationDateTime = LocalDateTime.now();
		this.lastUpateDateTime = LocalDateTime.now();
	}

	public long getOrderID() {
		return orderID;
	}

	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ProductLine getItem() {
		return item;
	}

	public void setItem(ProductLine item) {
		this.item = item;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
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

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public LocalDateTime getLastUpateDateTime() {
		return lastUpateDateTime;
	}

	public void setLastUpateDateTime(LocalDateTime lastUpateDateTime) {
		this.lastUpateDateTime = lastUpateDateTime;
	}	
	
}
