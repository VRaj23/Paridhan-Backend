package varadraj.common.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import varadraj.common.model.city.City;

public interface CityRepository extends CrudRepository<City, Long>{
	
	Optional<City> findByCityID(long cityID);

}
