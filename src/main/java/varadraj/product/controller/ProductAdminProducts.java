package varadraj.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.common.model.JsonResponse;
import varadraj.product.model.ProductCreationRequest;
import varadraj.product.service.ProductService;

@RestController
@RequestMapping("/product/admin/products")
public class ProductAdminProducts {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/addProduct")
	public JsonResponse addProduct(@RequestBody ProductCreationRequest request) {
		if(!productService.isValidRequest(request)) {
			return new JsonResponse(400,"Invalid Product creation request");
		}
		productService.addProduct(request);
		return new JsonResponse(201,"Product Created");
	}

}
