package varadraj.product.repository;

import org.springframework.data.repository.CrudRepository;

import varadraj.product.model.Size;

public interface SizeRepository extends CrudRepository<Size, Long>{
	
	public Size findBySizeCharacter(String sizeCharacter);
	public Size findBySizeID(long sizeID);

}
