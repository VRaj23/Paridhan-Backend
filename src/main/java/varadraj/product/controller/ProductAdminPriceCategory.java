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
import varadraj.product.model.PriceCategory;
import varadraj.product.service.PriceCategoryService;

@RestController
@CrossOrigin
@PreAuthorize(value = "hasRole('ADMIN')")
@RequestMapping("/auth/admin/product/priceCategory")
public class ProductAdminPriceCategory {

	@Autowired
	private PriceCategoryService priceCategoryService;
	
	@PostMapping("/add")
	public JsonResponse<Void> addPriceCategory(@RequestBody PriceCategory priceCategory) {
		if(priceCategory.getUpperLimit() == 0)
			return new JsonResponse<Void>(400
					, JsonResponseMessage.INVALID_INPUT
					, null);
		
		priceCategoryService.addPriceCategory(priceCategory);
		return new JsonResponse<Void>(201
				, JsonResponseMessage.CREATED
				, null);
	}
}
