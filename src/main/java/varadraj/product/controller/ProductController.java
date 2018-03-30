package varadraj.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import varadraj.product.model.Brand;
import varadraj.product.model.Color;
import varadraj.product.model.ProductHeader;
import varadraj.product.model.ProductModel;
import varadraj.product.model.ProductType;
import varadraj.product.model.Size;
import varadraj.product.service.BrandService;
import varadraj.product.service.ColorService;
import varadraj.product.service.ImageService;
import varadraj.product.service.PriceCategoryService;
import varadraj.product.service.ProductService;
import varadraj.product.service.ProductTypeService;
import varadraj.product.service.SizeService;
import varadraj.product.storage.StorageService;
import varadraj.product.util.ImageUtil;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
	
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
	@Autowired
	private ImageService imageService;

	
	@RequestMapping("/")
	public String index() {
		return "Product API";
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
	
	@RequestMapping("/products")
	public List<ProductModel> getAllProduct(){
		return productService.getAllProducts();
	}

	@PostMapping("/upload")
	public String handleFileUpload(@RequestParam("image") MultipartFile file,
			@RequestParam("ProductTypeID") String productTypeID){
		
		if(storageService.isValidFile(file) && ImageUtil.isImage(file)) {
			Long imageID = imageService.addImageReturnID(new Long(productTypeID),storageService.getFilename(file));
			if(storageService.store(file, imageID.toString())) {
				return "Image Uploaded";
			}
		}
		return "Could not save image";
    }

	@RequestMapping("/addDummyData")
	public void addDummyDate() {
		brandService.addDummyBrandData();
		pTypeService.addDummyTypeData();
		colorService.addColorDummyData();
		priceService.addDummyPriceCategoryData();
		sizeService.addDummySizeData();		
		productService.addDummyProductData();
	}

}
