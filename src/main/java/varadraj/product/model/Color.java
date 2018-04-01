package varadraj.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="product_color")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Color {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long colorID;
	
	@Column(unique=true)
	private String name;
	
	private String value;
	
	public Color() {}

	public Color(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(!Color.class.isAssignableFrom(obj.getClass()))
			return false;
		
		final Color c = (Color)obj;
		if(this.getColorID() != c.getColorID())
			return false;
		return true;
	}

	public long getColorID() {
		return colorID;
	}

	public void setColorID(long colorID) {
		this.colorID = colorID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


	
}
