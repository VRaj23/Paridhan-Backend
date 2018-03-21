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
	private String name;
	private int size_id;
	private int color_id;
	private int pattern_id;
	private int style_id;
	private boolean availability = true;
	private LocalDateTime creation_datetime;
	private LocalDateTime last_update_datetime;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "header_id")
	@JsonManagedReference
	private ProductHeader productHeader;
	
	public ProductLine() {}

	public ProductLine(long line_id, String name, int size_id, int color_id, int pattern_id, int style_id,
			boolean availability, LocalDateTime creation_datetime, LocalDateTime last_update_datetime) {
		super();
		this.line_id = line_id;
		this.name = name;
		this.size_id = size_id;
		this.color_id = color_id;
		this.pattern_id = pattern_id;
		this.style_id = style_id;
		this.availability = availability;
		this.creation_datetime = creation_datetime;
		this.last_update_datetime = last_update_datetime;
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

	public int getSize_id() {
		return size_id;
	}

	public void setSize_id(int size_id) {
		this.size_id = size_id;
	}

	public int getColor_id() {
		return color_id;
	}

	public void setColor_id(int color_id) {
		this.color_id = color_id;
	}

	public int getPattern_id() {
		return pattern_id;
	}

	public void setPattern_id(int pattern_id) {
		this.pattern_id = pattern_id;
	}

	public int getStyle_id() {
		return style_id;
	}

	public void setStyle_id(int style_id) {
		this.style_id = style_id;
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

	public LocalDateTime getLast_update_datetime() {
		return last_update_datetime;
	}

	public void setLast_update_datetime(LocalDateTime last_update_datetime) {
		this.last_update_datetime = last_update_datetime;
	}
	
	
	
}
