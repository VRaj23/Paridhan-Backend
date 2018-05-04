package varadraj.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.common.model.City;
import varadraj.common.repository.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepo;

//VALIDATIONS
	private boolean isValidCity(City city) {
		if(city.getName() == null)
			return false;
		if(city.getState() == null)
			return false;
		
		return true;
	}
	
//CREATE
	public City addCity(City city) {
		City savedCity = null;
		
		if(this.isValidCity(city)) {
			savedCity = cityRepo.save(city);
		}
		
		return savedCity;
	}

}
