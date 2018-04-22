package varadraj.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.model.JsonResponse;
import varadraj.product.model.Size;
import varadraj.product.service.SizeService;

@RestController
@RequestMapping("/product/admin/size")
public class ProductAdminSize {

	@Autowired
	private SizeService sizeService;
	
	@PostMapping("/addSize")
	public JsonResponse addSize(@RequestBody Size size) {
		if(size.getSizeCharacter() == null || size.getSizeNumber() == 0)
			return new JsonResponse(400,"Invalid Size Object");

		sizeService.addSize(size);
		return new JsonResponse(201, "New size created");
	}
}
