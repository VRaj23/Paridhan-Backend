package varadraj.repository;

import org.springframework.data.repository.CrudRepository;

import varadraj.model.Size;

public interface SizeRepository extends CrudRepository<Size, Long>{
	
	public Size findBySizeCharacter(String sizeCharacter);

}
