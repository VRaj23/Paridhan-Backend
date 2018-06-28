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
import varadraj.common.model.ResponseMessage;
import varadraj.common.model.city.CityCreationRequest;
import varadraj.common.model.state.StateCreationRequest;
import varadraj.common.service.CityService;
import varadraj.common.service.StateService;
import varadraj.exception.InvalidInputException;;

@RestController
@CrossOrigin
@PreAuthorize(value = "hasRole('ADMIN')")
@RequestMapping("/auth/admin/address")
public class AddressAdmin {
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private CityService cityService;
	
	@PostMapping("/state")
	public JsonResponse<Void> addState(@RequestBody StateCreationRequest stateRequest) {
		try {
			if ( stateService.addState( Optional.ofNullable(stateRequest) ).isPresent() )
				return new JsonResponse<Void>(201, ResponseMessage.CREATED, null);
			else
				return new JsonResponse<Void>(500, ResponseMessage.ERROR, null);
		}catch(InvalidInputException e) {
			return new JsonResponse<Void>(400, ResponseMessage.INVALID_INPUT, null);
		}
		
	}
	
	@PostMapping("/city")
	public JsonResponse<Void> addCity(@RequestBody CityCreationRequest cityCreationRequest) {
		try {
			if ( cityService.addCity( Optional.ofNullable(cityCreationRequest)).isPresent() )
				return new JsonResponse<Void>(201, ResponseMessage.CREATED, null);
			else
				return new JsonResponse<Void>(500, ResponseMessage.ERROR, null);
		}catch(InvalidInputException e) {
			return new JsonResponse<Void>(400, ResponseMessage.INVALID_INPUT, null);
		}
	}

}
