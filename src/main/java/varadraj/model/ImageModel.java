package varadraj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="product_image")
public class ImageModel {

	@Id
	@Column(name="image_id")
	@GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
	private long imageID;
	
	
	public ImageModel() {}


	public long getImageID() {
		return imageID;
	}


	public void setImageID(long imageID) {
		this.imageID = imageID;
	}
}
