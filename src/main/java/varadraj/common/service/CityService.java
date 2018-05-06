package varadraj.common.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.common.model.city.City;
import varadraj.common.model.city.CityCreationRequest;
import varadraj.common.model.state.State;
import varadraj.common.repository.CityRepository;
import varadraj.exception.InvalidInputException;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepo;
	
	@Autowired
	private StateService stateService;

	
//CREATE
	public Optional<City> addCity(Optional<CityCreationRequest> cityRequest) throws InvalidInputException{

		String cityName = cityRequest.map(CityCreationRequest::getCityName).orElseThrow(InvalidInputException::new);
		
		long stateID = cityRequest.map(CityCreationRequest::getStateID).orElseThrow(InvalidInputException::new);
		Optional<State> stateInDB = stateService.findByStateID(stateID); 
		State state = null;
		if(stateInDB.isPresent())
			state = stateInDB.get();
		else 
			throw new InvalidInputException();
		
		return Optional.ofNullable( cityRepo.save( new City(cityName, state) ) );
	}

	
//READ
	public Optional<City> findByCityID(long cityID) {
		return cityRepo.findByCityID(cityID);
	}
}
