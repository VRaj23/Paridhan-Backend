package varadraj.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.common.model.city.City;
import varadraj.common.model.city.CityCreationRequest;
import varadraj.common.repository.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepo;
	
	@Autowired
	private StateService stateService;

//VALIDATIONS
	private boolean isValidCityRequest(CityCreationRequest cityRequest) {
		if(cityRequest == null)
			return false;
		if(cityRequest.getCityName() == null)
			return false;
		if(stateService.findByStateID(cityRequest.getStateID()) == null)
			return false;
		
		return true;
	}
	
//CREATE
	public City addCity(CityCreationRequest cityRequest) {
		City savedCity = null;
		
		if(this.isValidCityRequest(cityRequest)) {
			City city = new City(cityRequest.getCityName()
					, stateService.findByStateID(cityRequest.getStateID()));
			
			savedCity = cityRepo.save(city);
		}
		
		return savedCity;
	}

	
//READ
	public City findByCityID(long cityID) {
		return cityRepo.findByCityID(cityID);
	}
}
