package varadraj.product.repository;

import org.springframework.data.repository.CrudRepository;

import varadraj.product.model.ImageModel;

public interface ImageRepository extends CrudRepository<ImageModel, Long>{
	
	public ImageModel findByImageID(long imageID);

}
