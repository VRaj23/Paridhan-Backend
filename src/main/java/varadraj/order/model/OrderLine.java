package varadraj.order.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import varadraj.product.model.ProductLine;

@Entity
@Table(name = "order_line")
public class OrderLine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_line_id")
	private long orderLineID;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	@JsonManagedReference
	private OrderHeader orderHeader;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_line_id")
	private ProductLine item;
	
	private int quantity = 1;

	public OrderLine() {}
	
	public OrderLine(OrderHeader orderHeader, ProductLine item, int quantity) {
		super();
		this.orderHeader = orderHeader;
		this.item = item;
		this.quantity = quantity;
	}

	public long getOrderLineID() {
		return orderLineID;
	}

	public void setOrderLineID(long orderLineID) {
		this.orderLineID = orderLineID;
	}

	public OrderHeader getOrderHeader() {
		return orderHeader;
	}

	public void setOrderHeader(OrderHeader orderHeader) {
		this.orderHeader = orderHeader;
	}

	public ProductLine getItem() {
		return item;
	}

	public void setItem(ProductLine item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
