package varadraj.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import varadraj.model.ProductHeader;
import varadraj.model.ProductLine;
import varadraj.model.ProductModel;
import varadraj.repository.ProductHeaderRepository;
import varadraj.repository.ProductLineRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductHeaderRepository pHRepo;
	@Autowired
	private ProductLineRepository pLRepo;
	
	public void addProduct() {
		ProductHeader header = new ProductHeader(1,1, 299.0, 1,0,1,true,5,LocalDateTime.now(),LocalDateTime.now());
		
		ProductLine line1 = new ProductLine(1,"line1",1,2, 3,4, true, LocalDateTime.now(),LocalDateTime.now());
		ProductLine line2 = new ProductLine(2,"line2",0,0, 0,0, false, LocalDateTime.now(),LocalDateTime.now());
		ProductLine line3 = new ProductLine(3,"line3",3,3, 3,3, true, LocalDateTime.now(),LocalDateTime.now());
		
		line1.setProductHeader(header);
		line2.setProductHeader(header);
		line3.setProductHeader(header);
		
		header.getProductLine().add(line1);
		header.getProductLine().add(line2);
		header.getProductLine().add(line3);
		
		pHRepo.save(header);
	}
	
	public List<ProductHeader> getAllHeader(){
		List<ProductHeader> headers = new ArrayList<>();
		pHRepo.findAll().forEach(headers::add);
		return headers;
	}
	
	public List<ProductModel> getAllProducts(){
		List<ProductModel> products = new ArrayList<>();
		for(ProductHeader header : pHRepo.findAll()) {
			ProductModel pModel = new ProductModel();
			pModel.setpHeader(header);
		}
		return products;
	}
	
}
