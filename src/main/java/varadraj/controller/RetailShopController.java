package varadraj.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import varadraj.model.ProductHeader;
import varadraj.model.ProductLine;
import varadraj.model.ProductModel;
import varadraj.model.ProductType;
import varadraj.service.ProductService;
import varadraj.service.ProductTypeService;

@RestController
@CrossOrigin
public class RetailShopController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductTypeService pTypeService;
	
	@RequestMapping("/")
	public String hello() {
		return "Hello";
	}

	@RequestMapping("/type")
	public List<ProductType> getAllTypes(){
		return pTypeService.getAllTypes();
	}
	
	@RequestMapping("/header")
	public List<ProductHeader> getAllHeaders() {
		return productService.getAllHeader();		
	}
	
	@RequestMapping("/product")
	public List<ProductModel> getAllProduct(){
		return productService.getAllProducts();
	}


	@RequestMapping("/addTypes")
	public void addType() {
		pTypeService.addDummyTypeData();
	}
	
	@RequestMapping("/addProducts")
	public void addProduct() {
		productService.addDummyProductData();
	}
}
