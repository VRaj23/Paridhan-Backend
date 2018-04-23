package varadraj.product.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import varadraj.product.serializer.ProductHeaderSerializer;

@Entity
@Table(name="product_header")
@JsonSerialize(using = ProductHeaderSerializer.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","priceCategory"})
public class ProductHeader {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="header_id")
	private long headerID;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "type_id")
	private ProductType productType;
	
	private double price;
	
	@Column(name="discount_percent")
	private double discountPercent = 0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="price_Category_id")
	private PriceCategory priceCategory;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "image_id")
	private ImageModel primaryImage;
	
	private boolean enabled = true;
	private LocalDateTime creation_datetime;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="productHeader",fetch=FetchType.LAZY)
	@JsonBackReference
	private Set<ProductLine> productLine = new HashSet<>();
	
	public ProductHeader(){}		

	public ProductHeader(ProductType productType, double price, double discountPercent, PriceCategory priceCategory, Brand brand,
			ImageModel image,boolean enabled, LocalDateTime creation_datetime) {
		super();
		this.productType = productType;
		this.price = price;
		this.discountPercent = discountPercent;
		this.priceCategory = priceCategory;
		this.primaryImage = image;
		this.brand = brand;
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
		
		if(this.getHeaderID() != ph.getHeaderID())
			return false;
		
		return true;
	}

	public long getHeaderID() {
		return headerID;
	}

	public void setHeaderID(long headerID) {
		this.headerID = headerID;
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


	public PriceCategory getPriceCategory() {
		return priceCategory;
	}


	public void setPriceCategory(PriceCategory priceCategory) {
		this.priceCategory = priceCategory;
	}


	public Brand getBrand() {
		return brand;
	}


	public void setBrand(Brand brand) {
		this.brand = brand;
	}


	public ImageModel getPrimaryImage() {
		return primaryImage;
	}


	public void setPrimaryImage(ImageModel primaryImage) {
		this.primaryImage = primaryImage;
	}


	public double getDiscountPercent() {
		return discountPercent;
	}


	public void setDiscountPercent(double discountPercent) {
		this.discountPercent = discountPercent;
	}
	
}
