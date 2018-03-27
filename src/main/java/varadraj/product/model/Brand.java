package varadraj.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="product_brand")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long brandID;
	
	@Column(unique=true)
	private String name;
	
	public Brand() {}

	public Brand(String name) {
		super();
		this.name = name;
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(!Brand.class.isAssignableFrom(obj.getClass()))
			return false;
		
		final Brand b = (Brand)obj;
		
		if(this.getBrandID() != b.getBrandID())
			return false;
		return true;
	}

	public long getBrandID() {
		return brandID;
	}

	public void setBrandID(long brandID) {
		this.brandID = brandID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
