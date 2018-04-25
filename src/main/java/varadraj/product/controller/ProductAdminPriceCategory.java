package varadraj.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.common.model.JsonResponse;
import varadraj.product.model.PriceCategory;
import varadraj.product.service.PriceCategoryService;

@RestController
@RequestMapping("/product/admin/priceCategory")
public class ProductAdminPriceCategory {

	@Autowired
	private PriceCategoryService priceCategoryService;
	
	@PostMapping("/addPriceCategory")
	public JsonResponse addPriceCategory(@RequestBody PriceCategory priceCategory) {
		if(priceCategory.getUpperLimit() == 0)
			return new JsonResponse(400,"UpperLimit of PriceCategory cannot be 0");
		
		priceCategoryService.addPriceCategory(priceCategory);
		return new JsonResponse(200, "Price Category Added");
	}
}
