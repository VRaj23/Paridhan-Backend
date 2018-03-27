package varadraj.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="product_price_category")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PriceCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long priceCategoryID;
	
	@Column(unique=true)
	private int lowerLimit;
	
	@Column(unique=true)
	private int upperLimit;
	
	public PriceCategory() {}	
	
	public PriceCategory(int lowerLimit, int upperLimit) {
		super();
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(!PriceCategory.class.isAssignableFrom(obj.getClass()))
			return false;
		
		final PriceCategory pc = (PriceCategory)obj;
		
		if(this.getPriceCategoryID() != pc.getPriceCategoryID())
			return false;
		return true;
		
	}


	public long getPriceCategoryID() {
		return priceCategoryID;
	}

	public void setPriceCategoryID(long priceCategoryID) {
		this.priceCategoryID = priceCategoryID;
	}

	public int getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(int lowerLimit) {
		this.lowerLimit = lowerLimit;
	}



	public int getUpperLimit() {
		return upperLimit;
	}



	public void setUpperLimit(int upperLimit) {
		this.upperLimit = upperLimit;
	}
	
	
	
}
