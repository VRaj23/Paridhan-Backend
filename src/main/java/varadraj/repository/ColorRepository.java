package varadraj.repository;

import org.springframework.data.repository.CrudRepository;

import varadraj.model.Color;

public interface ColorRepository extends CrudRepository<Color, Long>{
	
	public Color findByName(String name);

}
