package varadraj.product.controller;

import java.util.List;

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

import varadraj.common.model.JsonResponse;
import varadraj.common.model.JsonResponseMessage;
import varadraj.product.model.Brand;
import varadraj.product.model.Color;
import varadraj.product.model.PriceCategory;
import varadraj.product.model.ProductHeader;
import varadraj.product.model.ProductLine;
import varadraj.product.model.ProductModel;
import varadraj.product.model.ProductType;
import varadraj.product.model.Size;
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


	@GetMapping("/type")
	public JsonResponse<List<ProductType>> getAllTypes(){
		return new JsonResponse<List<ProductType>> (200
				,JsonResponseMessage.OK
				,pTypeService.getAllTypes());
	}
	
	@GetMapping("/color")
	public JsonResponse<List<Color>> getAllColor(){
		return new JsonResponse<List<Color>> (200
				,JsonResponseMessage.OK
				,colorService.getAllColor());
	}
	
	@GetMapping("/size")
	public JsonResponse<List<Size>> getAllSize(){
		return new JsonResponse<List<Size>>(200
				,JsonResponseMessage.OK
				,sizeService.getAllSize());
	}
	
	@GetMapping("/brand")
	public JsonResponse<List<Brand>> getAllBrand(){
		return new JsonResponse<List<Brand>>(200
				,JsonResponseMessage.OK
				,brandService.getAllBrand());
	}
	
	@GetMapping("/header")
	public JsonResponse<List<ProductHeader>> getAllHeaders(@RequestParam("t") String typeID) {
		return new JsonResponse<List<ProductHeader>>(200
				,JsonResponseMessage.OK
				,productService.findByTypeID(typeID));		
	}
	
	@GetMapping("/line")
	public JsonResponse<List<ProductLine>> getAllLines(@RequestParam("h") String header_id){
		return new JsonResponse<List<ProductLine>>(200
				,JsonResponseMessage.OK
				,productService.getAllLines(header_id));
	}
	
	@GetMapping("/products")
	public JsonResponse<List<ProductModel>> getAllProduct(){
		return new JsonResponse<List<ProductModel>>(200
				,JsonResponseMessage.OK
				,productService.getAllProducts());
	}
	
	@GetMapping("/priceCategory")
	public JsonResponse<List<PriceCategory>> getAllPriceCategory() {
		return new JsonResponse<List<PriceCategory>>(200
				,JsonResponseMessage.OK
				,priceCategoryService.getAllPriceCategory());
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
