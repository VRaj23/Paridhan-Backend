package varadraj.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.common.model.JsonResponse;
import varadraj.common.model.State;
import varadraj.common.service.StateService;

@RestController
@CrossOrigin
@PreAuthorize(value = "hasRole('ADMIN')")
@RequestMapping("/auth/admin/address")
public class AddressAdmin {
	
	@Autowired
	private StateService stateService;
	
	@PostMapping("/state")
	public JsonResponse addState(@RequestBody State state) {
		
		State savedState = stateService.addState(state);
		
		if(savedState == null)
			return new JsonResponse(500, "State not saved");
		else
			return new JsonResponse(201,"State saved Successfully");
	}

}
