package varadraj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.model.ProductHeader;
import varadraj.model.ProductModel;
import varadraj.service.ProductService;

@RestController
@CrossOrigin
public class RetailShopController {

	@Autowired
	private ProductService productService;

	@RequestMapping("/")
	public List<ProductHeader> getAll() {
		return productService.getAllHeader();		
	}
}
