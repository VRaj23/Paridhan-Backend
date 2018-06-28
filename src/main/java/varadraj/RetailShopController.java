package varadraj;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.common.model.JsonResponse;
import varadraj.common.model.ResponseMessage;

@RestController
@CrossOrigin
public class RetailShopController {
	
	@GetMapping("/test")
	public JsonResponse<String> hello() {
		return new JsonResponse<String>(200,ResponseMessage.OK,"Paridhan Backend");
	}
}