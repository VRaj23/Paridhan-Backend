package varadraj.common.controller;

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

@RestController
@CrossOrigin
@PreAuthorize(value = "hasRole('USER')")
@RequestMapping("/auth/customer/addDeliveryAddress")
public class AddDeliveryAddress {
	
	@Autowired
	private AddressService addressService;
	
	@PostMapping
	public JsonResponse<Long> addDeliveryAddress(@RequestBody AddressCreationRequest request) {
		Address savedAddress = addressService.addAddress(request);
		if(savedAddress == null)
			return new JsonResponse<Long>(400, JsonResponseMessage.INVALID_INPUT,null);
		else
			return new JsonResponse<Long>(201, JsonResponseMessage.OK,savedAddress.getAddressID());
	}

}
