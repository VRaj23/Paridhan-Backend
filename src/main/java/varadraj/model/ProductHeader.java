package varadraj.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="product_header")
public class ProductHeader {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long header_id;
	private int type_id;
	private double price;
	private int price_category_id;
	private double discount_percentage = 0;
	private int fabric_id;
	private boolean enabled = true;
	private int brand_id;
	private LocalDateTime creation_datetime;
	private LocalDateTime last_update_datetime;

	@OneToMany(cascade=CascadeType.ALL,mappedBy="productHeader",fetch=FetchType.LAZY)
	@JsonBackReference
	private Set<ProductLine> productLine = new HashSet<>();
	
	public ProductHeader(){}

	public ProductHeader(long header_id, int type_id, double price, int price_category_id, double discount,
			int fabric_id, boolean enabled, int brand_id, LocalDateTime creation_datetime,
			LocalDateTime last_update_datetime) {
		super();
		this.header_id = header_id;
		this.type_id = type_id;
		this.price = price;
		this.price_category_id = price_category_id;
		this.discount_percentage = discount;
		this.fabric_id = fabric_id;
		this.enabled = enabled;
		this.brand_id = brand_id;
		this.creation_datetime = creation_datetime;
		this.last_update_datetime = last_update_datetime;
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if (!ProductHeader.class.isAssignableFrom(obj.getClass()))
			return false;
		
		final ProductHeader ph = (ProductHeader) obj;
		
		if(this.getHeader_id() != ph.getHeader_id())
			return false;
		
		return true;
	}

	public long getHeader_id() {
		return header_id;
	}

	public void setHeader_id(long header_id) {
		this.header_id = header_id;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getPrice_category_id() {
		return price_category_id;
	}

	public void setPrice_category_id(int price_category_id) {
		this.price_category_id = price_category_id;
	}

	public double getDiscount() {
		return discount_percentage;
	}

	public void setDiscount(double discount) {
		this.discount_percentage = discount;
	}

	public int getFabric_id() {
		return fabric_id;
	}

	public void setFabric_id(int fabric_id) {
		this.fabric_id = fabric_id;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}

	public LocalDateTime getCreation_datetime() {
		return creation_datetime;
	}

	public void setCreation_datetime(LocalDateTime creation_datetime) {
		this.creation_datetime = creation_datetime;
	}

	public LocalDateTime getLast_update_datetime() {
		return last_update_datetime;
	}

	public void setLast_update_datetime(LocalDateTime last_update_datetime) {
		this.last_update_datetime = last_update_datetime;
	}
	
	public double getDiscount_percentage() {
		return discount_percentage;
	}

	public void setDiscount_percentage(double discount_percentage) {
		this.discount_percentage = discount_percentage;
	}

	public Set<ProductLine> getProductLine() {
		return productLine;
	}

	public void setProductLine(Set<ProductLine> productLine) {
		this.productLine = productLine;
	}	
	
}
