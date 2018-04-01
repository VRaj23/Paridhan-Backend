package varadraj.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "product_type")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="type_id")
	private long typeID;
	
	@Column(unique=true)
	private String description;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="image_id")
	@JsonManagedReference
	private ImageModel typeImage;
	
	public ImageModel getTypeImage() {
		return typeImage;
	}

	public ProductType() {}
		
	public ProductType(String description) {
		super();
		this.description = description;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(!ProductType.class.isAssignableFrom(obj.getClass()))
			return false;
		
		final ProductType pt = (ProductType) obj;
		
		if(this.getTypeID() != pt.getTypeID())
			return false;
		
		return true;
	}


	public long getTypeID() {
		return typeID;
	}

	public void setType_id(long type_id) {
		this.typeID = type_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setTypeImage(ImageModel typeImage) {
		this.typeImage = typeImage;
	}


	public void setTypeID(long typeID) {
		this.typeID = typeID;
	}
}
