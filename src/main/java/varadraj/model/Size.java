package varadraj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product_size")
public class Size {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long sizeID;
	@Column(unique=true)
	private String sizeCharacter;
	private int sizeNumber;
	
	public Size() {}

	public Size(String sizeCharacter, int sizeNumber) {
		super();
		this.sizeCharacter = sizeCharacter;
		this.sizeNumber = sizeNumber;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(!Size.class.isAssignableFrom(obj.getClass()))
			return false;
		
		final Size s = (Size)obj;
		if(this.getSizeID() != s.getSizeID())
			return false;
		return true;
	}

	public long getSizeID() {
		return sizeID;
	}

	public void setSizeID(long sizeID) {
		this.sizeID = sizeID;
	}

	public String getSizeCharacter() {
		return sizeCharacter;
	}

	public void setSizeCharacter(String sizeCharacter) {
		this.sizeCharacter = sizeCharacter;
	}

	public int getSizeNumber() {
		return sizeNumber;
	}

	public void setSizeNumber(int sizeNumber) {
		this.sizeNumber = sizeNumber;
	}
	
	
	
	
}
