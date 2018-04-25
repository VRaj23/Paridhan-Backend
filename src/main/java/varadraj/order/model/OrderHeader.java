package varadraj.order.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import varadraj.common.model.Address;
import varadraj.user.model.Customer;

@Entity
@Table(name="order_header")
public class OrderHeader {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_header_id")
	private long orderHeaderID;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	private int statusID;
	
	private double amount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery_address_id")
	private Address deliveryAddress;
	
	private LocalDateTime creationDateTime;
	private LocalDateTime lastUpateDateTime;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="orderHeader")
	@JsonBackReference
	private List<OrderLine> orderLine = new ArrayList<>();

	public OrderHeader() {}	

	public OrderHeader(Customer customer, int statusID, double amount, Address deliveryAddress) {
		super();
		this.customer = customer;
		this.statusID = statusID;
		this.amount = amount;
		this.deliveryAddress = deliveryAddress;
		this.creationDateTime = LocalDateTime.now();
		this.lastUpateDateTime = LocalDateTime.now();
	}

	public long getOrderHeaderID() {
		return orderHeaderID;
	}

	public void setOrderHeaderID(long orderHeaderID) {
		this.orderHeaderID = orderHeaderID;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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

	public List<OrderLine> getOrderLine() {
		return orderLine;
	}

	public void setOrderLine(List<OrderLine> orderLine) {
		this.orderLine = orderLine;
	}


}
