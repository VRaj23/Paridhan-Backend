package varadraj.product.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import varadraj.product.model.Brand;
import varadraj.product.model.Color;
import varadraj.product.model.ProductHeader;
import varadraj.product.model.ProductLine;
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
	public List<ProductHeader> getAllHeaders(@RequestParam("t") String typeID) {
		return productService.findByTypeID(typeID);		
	}
	
	@RequestMapping("/line")
	public List<ProductLine> getAllLines(@RequestParam("h") String header_id){
		return productService.getAllLines(header_id);
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
			try {
				storageService.storeImage(file, imageID.toString());
				return "Image uploaded successfully";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "Could not save image";
    }
	
	@GetMapping("/download/{imageID}")
	public ResponseEntity<Resource> getFile(@PathVariable long imageID){
		Resource image = storageService.loadFile(imageID);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.IMAGE_PNG);
		return ResponseEntity.ok()
				.headers(header)
				.body(image);
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
