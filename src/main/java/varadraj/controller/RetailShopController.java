package varadraj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import varadraj.model.Brand;
import varadraj.model.Color;
import varadraj.model.ProductHeader;
import varadraj.model.ProductModel;
import varadraj.model.ProductType;
import varadraj.model.Size;
import varadraj.service.BrandService;
import varadraj.service.ColorService;
import varadraj.service.PriceCategoryService;
import varadraj.service.ProductService;
import varadraj.service.ProductTypeService;
import varadraj.service.SizeService;
import varadraj.storage.StorageService;

@RestController
@CrossOrigin
public class RetailShopController {

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
	private PriceCategoryService priceService;
	@Autowired
	private StorageService storageService;
	
	@RequestMapping("/")
	public String hello() {
		return "Retail Shop Backend";
	}

	@RequestMapping("/type")
	public List<ProductType> getAllTypes(){
		return pTypeService.getAllTypes();
	}
	
	@RequestMapping("/color")
	public List<Color> getAllColor(){
		return colorService.getAllColor();
	}
	
	@RequestMapping("/size")
	public List<Size> getAllSize(){
		return sizeService.getAllSize();
	}
	
	@RequestMapping("/brand")
	public List<Brand> getAllBrand(){
		return brandService.getAllBrand();
	}
	
	@RequestMapping("/header")
	public List<ProductHeader> getAllHeaders() {
		return productService.getAllHeader();		
	}
	
	@RequestMapping("/product")
	public List<ProductModel> getAllProduct(){
		return productService.getAllProducts();
	}

	@PostMapping("/upload")
	public void handleFileUpload(@RequestParam("file") MultipartFile file){
        storageService.store(file);
        
    }

	@RequestMapping("/addDummyData")
	public void addDummyDate() {
		brandService.addDummyBrandData();
		sizeService.populateData();
		colorService.addColorDummyData();
		pTypeService.addDummyTypeData();
		priceService.addDummyPriceCategoryData();
		productService.addDummyProductData();
	}

}
