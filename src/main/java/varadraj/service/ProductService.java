package varadraj.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import varadraj.model.ProductHeader;
import varadraj.model.ProductLine;
import varadraj.model.ProductModel;
import varadraj.model.ProductType;
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
		ProductHeader header = new ProductHeader(shirt, 299.0,true,LocalDateTime.now());
		ProductLine line1 = new ProductLine("Shirt_1", true, LocalDateTime.now(), header);
		header.getProductLine().add(line1);
		ProductLine line2 = new ProductLine("Shirt_2", true, LocalDateTime.now(), header);
		header.getProductLine().add(line2);
		pHRepo.save(header);
		
		ProductType pant = pTypeService.findByDescription("Pant");
		ProductHeader header2 = new ProductHeader(pant, 499.0,true,LocalDateTime.now());
		ProductLine pant1 = new ProductLine("Pant_1", false, LocalDateTime.now(), header2);
		header.getProductLine().add(pant1);
		ProductLine pant2 = new ProductLine("Pant_2", true, LocalDateTime.now(), header2);
		header.getProductLine().add(pant2);
		pHRepo.save(header2);
	}
}
