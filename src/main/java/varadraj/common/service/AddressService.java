package varadraj.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.common.model.address.Address;
import varadraj.common.model.address.AddressCreationRequest;
import varadraj.common.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private CityService cityService;

//VALIDATION
	private boolean isValidAddressCreationRequest(AddressCreationRequest addressCreationRequest) {
		if(addressCreationRequest == null)
			return false;
		if(addressCreationRequest.getArea() == null)
			return false;
		if(addressCreationRequest.getHouseNumber() == null)
			return false;
		if(addressCreationRequest.getCityID() < 1)
			return false;
		if(cityService.findByCityID(addressCreationRequest.getCityID()) == null)
			return false;
		if((addressCreationRequest.getPincode() < 100000) || (addressCreationRequest.getPincode() > 999999))
			return false;
		return true;
	}
	
//CREATE
	public Address addAddress(AddressCreationRequest addressCreationRequest) {
		Address savedAddress = null;
		if(this.isValidAddressCreationRequest(addressCreationRequest)) {
			String landmark = addressCreationRequest.getLandmark();
			if (landmark == null)
				landmark = "";
			Address address = new Address(
					addressCreationRequest.getHouseNumber()
					,addressCreationRequest.getArea()
					,landmark
					,cityService.findByCityID(addressCreationRequest.getCityID())
					,addressCreationRequest.getPincode());
			
			savedAddress = addressRepo.save(address);
		}
		return savedAddress;
	}
//READ
	
	public Address findByAddressID(long addressID) {
		return addressRepo.findByAddressID(addressID);
	}
	
//UPDATE
//DELETE

}
