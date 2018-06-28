package varadraj.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.common.model.JsonResponse;
import varadraj.common.model.ResponseMessage;
import varadraj.common.model.city.CityResponse;
import varadraj.common.service.CityService;

@RestController
@CrossOrigin
@RequestMapping("/address")
public class AddressInfo {
	
	@Autowired
	private CityService cityService;
	
	@GetMapping("/cities")
	public JsonResponse<List<CityResponse>> getCities(){
		return new JsonResponse<List<CityResponse>>(200, ResponseMessage.OK, cityService.getCities());
	}

}
