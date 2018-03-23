package varadraj.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="product_line")
public class ProductLine {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long line_id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "header_id")
	@JsonManagedReference
	private ProductHeader productHeader;
	
	private String name;
	private boolean availability = true;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "color_id")
	private Color color;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "size_id")
	private Size size;
	
	private LocalDateTime creation_datetime;
	private LocalDateTime last_update_datetime;
	
	
	public ProductLine() {}

	
	public ProductLine(ProductHeader productHeader, String name, boolean availability, Color color, Size size,
			LocalDateTime creation_datetime) {
		super();
		this.productHeader = productHeader;
		this.name = name;
		this.availability = availability;
		this.color = color;
		this.size = size;
		this.creation_datetime = creation_datetime;
		this.last_update_datetime = creation_datetime;
	}



	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!ProductLine.class.isAssignableFrom(obj.getClass()))
			return false;
		
		final ProductLine pl = (ProductLine) obj;
		
		if(this.getLine_id() != pl.getLine_id())
			return false;
		
		return true;
	}

	public ProductHeader getProductHeader() {
		return productHeader;
	}

	public void setProductHeader(ProductHeader productHeader) {
		this.productHeader = productHeader;
	}


	public long getLine_id() {
		return line_id;
	}

	public void setLine_id(long line_id) {
		this.line_id = line_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public boolean isAvailability() {
		return availability;
	}


	public void setAvailability(boolean availability) {
		this.availability = availability;
	}


	public LocalDateTime getCreation_datetime() {
		return creation_datetime;
	}

	public void setCreation_datetime(LocalDateTime creation_datetime) {
		this.creation_datetime = creation_datetime;
	}


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}


	public Size getSize() {
		return size;
	}


	public void setSize(Size size) {
		this.size = size;
	}


	public LocalDateTime getLast_update_datetime() {
		return last_update_datetime;
	}


	public void setLast_update_datetime(LocalDateTime last_update_datetime) {
		this.last_update_datetime = last_update_datetime;
	}
	
	
}
