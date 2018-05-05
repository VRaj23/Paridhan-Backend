package varadraj.common.repository;

import org.springframework.data.repository.CrudRepository;

import varadraj.common.model.city.City;

public interface CityRepository extends CrudRepository<City, Long>{
	
	City findByCityID(long cityID);

}
