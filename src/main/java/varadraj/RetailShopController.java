package varadraj;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.model.JsonResponse;

@RestController
@CrossOrigin
public class RetailShopController {
	
	@GetMapping("/")
	public JsonResponse hello() {
		return new JsonResponse(200,"Retail Shop Backend");
	}
}
