package varadraj.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import varadraj.product.model.Color;

public interface ColorRepository extends JpaRepository<Color, Long>{
	
	public Color findByColorID(long colorID);
	public Color findByValue(String value);

}
