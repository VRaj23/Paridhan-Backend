package varadraj.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.common.model.city.City;
import varadraj.common.model.city.CityCreationRequest;
import varadraj.common.model.JsonResponse;
import varadraj.common.model.JsonResponseMessage;
import varadraj.common.model.state.State;
import varadraj.common.model.state.StateCreationRequest;
import varadraj.common.service.CityService;
import varadraj.common.service.StateService;

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
		
		State savedState = stateService.addState(stateRequest);
		
		return savedState == null ? 
				new JsonResponse<Void>(400,JsonResponseMessage.INVALID_INPUT ,null) 
				: new JsonResponse<Void>(201,JsonResponseMessage.CREATED,null);
		
	}
	
	@PostMapping("/city")
	public JsonResponse<Void> addCity(@RequestBody CityCreationRequest cityCreationRequest) {
		
		City savedCity = cityService.addCity(cityCreationRequest);
		
		if (savedCity == null) {
			return new JsonResponse<Void>(400, JsonResponseMessage.INVALID_INPUT,null);}
		else {
			return new JsonResponse<Void>(201, JsonResponseMessage.OK,null);
		}
			
	}

}
