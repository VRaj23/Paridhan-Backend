package varadraj.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.common.model.JsonResponse;
import varadraj.common.model.JsonResponseMessage;
import varadraj.product.model.Brand;
import varadraj.product.service.BrandService;

@RestController
@CrossOrigin
@PreAuthorize(value = "hasRole('ADMIN')")
@RequestMapping("/auth/admin/product/brand")
public class ProductAdminBrand {
	
	@Autowired
	private BrandService brandService;
	
	@PostMapping("/add")
	public JsonResponse<Void> addBrand(@RequestBody Brand brand) {
		if(brand.getName() == null)
			return new JsonResponse<Void>(400
					, JsonResponseMessage.INVALID_INPUT
					, null);
		
		brandService.addBrand(brand);
		return new JsonResponse<Void>(201
				, JsonResponseMessage.CREATED
				, null);
	}

}
