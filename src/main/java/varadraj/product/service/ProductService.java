package varadraj.product.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.product.model.Brand;
import varadraj.product.model.Color;
import varadraj.product.model.HeaderCreationRequest;
import varadraj.product.model.ImageModel;
import varadraj.product.model.LineCreationRequest;
import varadraj.product.model.ProductCreationRequest;
import varadraj.product.model.ProductHeader;
import varadraj.product.model.ProductLine;
import varadraj.product.model.ProductModel;
import varadraj.product.model.ProductType;
import varadraj.product.model.Size;
import varadraj.product.repository.ProductHeaderRepository;
import varadraj.product.repository.ProductLineRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductHeaderRepository pHRepo;
	@Autowired
	private ProductLineRepository pLRepo;
	@Autowired
	private ProductTypeService pTypeService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private PriceCategoryService priceService;
	@Autowired
	private ColorService colorService;
	@Autowired
	private SizeService sizeService;
	@Autowired
	private ImageService imageService;
	
	public boolean isValidRequest(ProductCreationRequest request) {
		if(request.getHeader().getPrice() <= 0)
			return false;
		if(request.getHeader().getDiscount()<0 || request.getHeader().getDiscount()>=100)
			return false;
		
		return true;
	}

//CREATE
	public void addProduct(ProductCreationRequest request) {
		ProductHeader header = pHRepo.save( createProductHeader(request.getHeader()) );
		
		for(LineCreationRequest lineRequest : request.getLines()) {
			pLRepo.save(createProductLine(lineRequest, header));
		}
	}
	
	private ProductHeader createProductHeader(HeaderCreationRequest headerRequest) {
		
		ProductType type = pTypeService.findByTypeID(headerRequest.getProductTypeID());
		Brand brand = brandService.findByBrandID(headerRequest.getBrandID());
		ImageModel image = imageService.findByImageID(headerRequest.getImageID());
		
		return new ProductHeader
				(type, 
				 headerRequest.getPrice(), 
				 headerRequest.getDiscount(), 
				 this.priceService.findByUpperLimit(1000), //TODO auto find PriceCategory
				 brand,
				 image, 
				 headerRequest.isEnabled(), 
				 LocalDateTime.now());
	}
	
	private ProductLine createProductLine(LineCreationRequest lineRequest,ProductHeader header) {
		
		Color color = colorService.findByColorID(lineRequest.getColorID());
		Size size = sizeService.findBySizeID(lineRequest.getSizeID());	
		
		return new ProductLine(
				 header,
				 lineRequest.getName(), 
				 lineRequest.isAvailable(), 
				 color, 
				 size, 
				 LocalDateTime.now());
	}
	
//READ
	public List<ProductHeader> getAllHeader(){
		List<ProductHeader> headers = new ArrayList<>();
		pHRepo.findAll().forEach(headers::add);
		return headers;
	}
	
	public List<ProductLine> getAllLines(String headerID){
		List<ProductLine> lines = new ArrayList<>();
		Optional<ProductHeader> ph = pHRepo.findById(new Long(headerID));
		if(ph.isPresent()) {
			pLRepo.findByProductHeader(ph.get()).forEach(lines::add);
		}
		return lines;
	}
	
	public List<ProductHeader> findByTypeID(String typeID) {
		List<ProductHeader> headers = new ArrayList<>();
		ProductType type = pTypeService.findByTypeID(new Long(typeID));
		pHRepo.findByProductType(type).forEach(headers::add);;
		return headers;
	}
	
	public List<ProductLine> getAllLines(ProductHeader ph){
		List<ProductLine> lines = new ArrayList<>();
		pLRepo.findByProductHeader(ph).forEach(lines::add);
		return lines;
	}
	
	
	public List<ProductModel> getAllProducts(){
		List<ProductModel> products = new ArrayList<>();
		for(ProductHeader header : pHRepo.findAll()) {
			ProductModel pModel = new ProductModel();
			pModel.setpHeader(header);
			pModel.setpLine(getAllLines(header));
			products.add(pModel);
		}
		return products;
	}
	
	public ProductLine findByLineID(long productLineID) {
		if(productLineID <=0 )
			return null;
		
		return pLRepo.findByLineID(productLineID);
	}
	
	public double getPrice(long productLineID) {
		return this.findByLineID(productLineID).getProductHeader().getPrice();
	}


//UPDATE
	
//DELETE
	
}
