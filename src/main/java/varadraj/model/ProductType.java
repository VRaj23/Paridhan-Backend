package varadraj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_type")
public class ProductType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long type_id;
	
	@Column(unique=true)
	private String description;
	
	public ProductType() {}
	
		
	public ProductType(String description) {
		super();
		this.description = description;
	}

	public ProductType(long type_id, String description) {
		super();
		this.type_id = type_id;
		this.description = description;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(!ProductType.class.isAssignableFrom(obj.getClass()))
			return false;
		
		final ProductType pt = (ProductType) obj;
		
		if(this.getType_id() != pt.getType_id())
			return false;
		
		return true;
	}


	public long getType_id() {
		return type_id;
	}

	public void setType_id(long type_id) {
		this.type_id = type_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
