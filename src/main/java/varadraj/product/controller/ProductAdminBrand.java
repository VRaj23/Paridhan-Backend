package varadraj.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.common.model.JsonResponse;
import varadraj.product.model.Brand;
import varadraj.product.service.BrandService;

@RestController
@RequestMapping("/product/admin/brand")
public class ProductAdminBrand {
	
	@Autowired
	private BrandService brandService;
	
	@PostMapping("/addBrand")
	public JsonResponse addBrand(@RequestBody Brand brand) {
		if(brand.getName() == null)
			return new JsonResponse(400, "No Brand Name provided. Brand creation Failed");
		
		brandService.addBrand(brand);
		return new JsonResponse(201,"Brand "+brand.getName()+" added");
	}

}
