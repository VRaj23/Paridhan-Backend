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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="product_header")
public class ProductHeader {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long header_id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "type_id")
	private ProductType productType;
	private double price;
	
	private boolean enabled = true;
	private LocalDateTime creation_datetime;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="productHeader",fetch=FetchType.LAZY)
	@JsonBackReference
	private Set<ProductLine> productLine = new HashSet<>();
	
	public ProductHeader(){}
	
	public ProductHeader(ProductType productType, double price, boolean enabled, LocalDateTime creation_datetime) {
		super();
		this.productType = productType;
		this.price = price;
		this.enabled = enabled;
		this.creation_datetime = creation_datetime;
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

	@ManyToOne(cascade=CascadeType.ALL)
	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public LocalDateTime getCreation_datetime() {
		return creation_datetime;
	}


	public void setCreation_datetime(LocalDateTime creation_datetime) {
		this.creation_datetime = creation_datetime;
	}


	public Set<ProductLine> getProductLine() {
		return productLine;
	}


	public void setProductLine(Set<ProductLine> productLine) {
		this.productLine = productLine;
	}

	
	
}
