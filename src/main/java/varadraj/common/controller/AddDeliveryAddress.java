package varadraj.common.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.common.model.JsonResponse;
import varadraj.common.model.JsonResponseMessage;
import varadraj.common.model.address.Address;
import varadraj.common.model.address.AddressCreationRequest;
import varadraj.common.service.AddressService;
import varadraj.exception.InvalidInputException;

@RestController
@CrossOrigin
@PreAuthorize(value = "hasRole('USER')")
@RequestMapping("/auth/customer/addDeliveryAddress")
public class AddDeliveryAddress {
	
	@Autowired
	private AddressService addressService;
	
	@PostMapping
	public JsonResponse<Long> addDeliveryAddress(@RequestBody AddressCreationRequest request) {
		try {
			Optional<Address> address = addressService.addAddress( Optional.ofNullable(request));
			if( address.isPresent() )
				return new JsonResponse<Long>(201
						,JsonResponseMessage.CREATED
						,address.map(Address::getAddressID).get() );
			else
				return new JsonResponse<Long>(500, JsonResponseMessage.ERROR,null);
		}catch(InvalidInputException e) {
			return new JsonResponse<Long>(400, JsonResponseMessage.INVALID_INPUT, null);
		}
	}

}
