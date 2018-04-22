package varadraj.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import varadraj.model.JsonResponse;
import varadraj.product.model.PriceCategory;
import varadraj.product.service.BrandService;
import varadraj.product.service.ColorService;
import varadraj.product.service.PriceCategoryService;
import varadraj.product.service.ProductService;
import varadraj.product.service.ProductTypeService;
import varadraj.product.service.SizeService;
import varadraj.product.storage.StorageService;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductGetInfo {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductTypeService pTypeService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private ColorService colorService;
	@Autowired
	private SizeService sizeService;
	@Autowired
	private StorageService storageService;
	@Autowired
	private PriceCategoryService priceCategoryService;

	
	@GetMapping("/")
	public JsonResponse index() {
		return new JsonResponse(200,"Product Info API");
	}	

	@GetMapping("/type")
	public JsonResponse getAllTypes(){
		return new JsonResponse (200,pTypeService.getAllTypes());
	}
	
	@GetMapping("/color")
	public JsonResponse getAllColor(){
		return new JsonResponse (200,colorService.getAllColor());
	}
	
	@GetMapping("/size")
	public JsonResponse getAllSize(){
		return new JsonResponse(200,sizeService.getAllSize());
	}
	
	@GetMapping("/brand")
	public JsonResponse getAllBrand(){
		return new JsonResponse(200,brandService.getAllBrand());
	}
	
	@GetMapping("/header")
	public JsonResponse getAllHeaders(@RequestParam("t") String typeID) {
		return new JsonResponse(200,productService.findByTypeID(typeID));		
	}
	
	@GetMapping("/line")
	public JsonResponse getAllLines(@RequestParam("h") String header_id){
		return new JsonResponse(200,productService.getAllLines(header_id));
	}
	
	@GetMapping("/products")
	public JsonResponse getAllProduct(){
		return new JsonResponse(200,productService.getAllProducts());
	}
	
	@GetMapping("/priceCategory")
	public JsonResponse getAllPriceCategory() {
		return new JsonResponse(200,priceCategoryService.getAllPriceCategory());
	}
	
	@GetMapping("/downloadImage/{imageID}")
	public ResponseEntity<Resource> getFile(@PathVariable long imageID){
		Resource image = storageService.loadFile(imageID);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.IMAGE_PNG);
		return ResponseEntity.ok()
				.headers(header)
				.body(image);
	}

}
