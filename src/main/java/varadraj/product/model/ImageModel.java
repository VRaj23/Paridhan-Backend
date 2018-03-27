package varadraj.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="product_image")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ImageModel {

	@Id
	@Column(name="image_id")
	@GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
	private long imageID;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id")
	private ProductType productType;
	
	private String name;
	
	
	public ImageModel() {}
	
	
	public ImageModel(ProductType productType, String name) {
		super();
		this.productType = productType;
		this.name = name;
	}



	public long getImageID() {
		return imageID;
	}


	public void setImageID(long imageID) {
		this.imageID = imageID;
	}


	public ProductType getProductType() {
		return productType;
	}


	public void setProductType(ProductType productType) {
		this.productType = productType;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
}
