package varadraj.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.common.model.Address;
import varadraj.common.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepo;
	
//CREATE
	public Address addAddress(Address address) {
		return addressRepo.save(address);
	}
//READ
//UPDATE
//DELETE

}
