package varadraj.common.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.common.model.address.Address;
import varadraj.common.model.address.AddressCreationRequest;
import varadraj.common.model.address.AddressResponse;
import varadraj.common.model.city.City;
import varadraj.common.repository.AddressRepository;
import varadraj.exception.InvalidInputException;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private CityService cityService;

//VALIDATION
	private int getPincodeIfValid(int pincode) throws InvalidInputException {
		if ( (pincode < 100_000) && (pincode > 1_000_000) )
			throw new InvalidInputException();
		else
			return pincode;
	}
	
	private City getCityIfValid(long cityID) throws InvalidInputException {
		Optional<City> cityInDB = cityService.findByCityID(cityID);
		if(cityInDB.isPresent())
			return cityInDB.get();
		else
			throw new InvalidInputException();
	}
	
//CREATE
	public Optional<Address> addAddress(Optional<AddressCreationRequest> request) throws InvalidInputException{
		
		City city = this.getCityIfValid(
				request.map(AddressCreationRequest::getCityID).orElseThrow(InvalidInputException::new));
		
		
		int pincode = this.getPincodeIfValid(
				request.map(AddressCreationRequest::getPincode).orElseThrow(InvalidInputException::new));
		
		return Optional.ofNullable(addressRepo.save(new Address(
				  request.map(AddressCreationRequest::getHouseNumber).orElseThrow(InvalidInputException::new)
				, request.map(AddressCreationRequest::getArea).orElseThrow(InvalidInputException::new)
				, request.map(AddressCreationRequest::getLandmark).orElse("")
				, city
				, pincode)));
	}
	
//READ
	
	public Optional<Address> findByAddressID(long addressID) {
		return addressRepo.findByAddressID(addressID);
	}

	public AddressResponse getAddressResponse(Address address) {
		return new AddressResponse(
				address.getAddressID(), 
				address.getHouseNumber(), 
				address.getArea(), 
				address.getLandmark(), 
				address.getCity().getCityName(), 
				address.getCity().getState().getStateName(), 
				address.getPincode());
	}
	
//UPDATE
//DELETE

}
