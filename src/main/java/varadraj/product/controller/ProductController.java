package varadraj.product.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
	
	@RequestMapping("/")
	public String index() {
		return "Product API";
	}
	
	@RequestMapping("/products")
	public String products() {
		return "Products";
	}

}
