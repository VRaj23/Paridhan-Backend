package varadraj.product.repository;

import org.springframework.data.repository.CrudRepository;

import varadraj.product.model.Color;

public interface ColorRepository extends CrudRepository<Color, Long>{
	
	public Color findByName(String name);

}
