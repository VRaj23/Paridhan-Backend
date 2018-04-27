package varadraj.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.common.model.JsonResponse;
import varadraj.product.model.Size;
import varadraj.product.service.SizeService;

@RestController
@PreAuthorize(value = "hasRole('ADMIN')")
@RequestMapping("/auth/admin/product/size")
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
