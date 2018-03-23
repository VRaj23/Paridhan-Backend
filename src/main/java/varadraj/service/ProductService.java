package varadraj.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.model.Brand;
import varadraj.model.Color;
import varadraj.model.PriceCategory;
import varadraj.model.ProductHeader;
import varadraj.model.ProductLine;
import varadraj.model.ProductModel;
import varadraj.model.ProductType;
import varadraj.model.Size;
import varadraj.repository.ProductHeaderRepository;
import varadraj.repository.ProductLineRepository;

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

	
	public List<ProductHeader> getAllHeader(){
		List<ProductHeader> headers = new ArrayList<>();
		pHRepo.findAll().forEach(headers::add);
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
	
	
	public void addDummyProductData() {
		ProductType shirt = pTypeService.findByDescription("Shirt");
		Brand polo = brandService.findByName("Polo");
		PriceCategory rs_0_499 = priceService.findByUpperLimit(499);
		ProductHeader header1 = new ProductHeader(shirt, 299.0,rs_0_499, polo, true,LocalDateTime.now());
		
		Color red = colorService.findByName("Red");
		Size m = sizeService.findSize("M");
		ProductLine line1 = new ProductLine(header1, "Polo_Shirt_499", true, red, m, LocalDateTime.now());
		header1.getProductLine().add(line1);
		
		Color black = colorService.findByName("Black");
		Size l = sizeService.findSize("L");
		ProductLine line2 = new ProductLine(header1, "Polo_Shirt_499", true, black, l, LocalDateTime.now());
		header1.getProductLine().add(line2);
		
		pHRepo.save(header1);
		
		ProductType pant = pTypeService.findByDescription("Pant");
		Brand spykar = brandService.findByName("Spykar");
		PriceCategory rs_0_999 = priceService.findByUpperLimit(999);
		ProductHeader header2 = new ProductHeader(pant, 899.0,rs_0_999,spykar,true,LocalDateTime.now());
		
		ProductLine line3 = new ProductLine(header2, "Spykar_Pant_899", true, black, l, LocalDateTime.now());
		header2.getProductLine().add(line3);
		
		pHRepo.save(header2);
		
	}
}
