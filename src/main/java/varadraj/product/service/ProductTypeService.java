package varadraj.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import varadraj.product.model.ProductType;
import varadraj.product.repository.ProductTypeRepository;

@Service
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductTypeService {

	@Autowired
	private ProductTypeRepository pTRepo;
	
	public void addDummyTypeData() {
		ProductType shirt = new ProductType("Shirt");
		pTRepo.save(shirt);
		
		ProductType pant = new ProductType("Pant");
		pTRepo.save(pant);
	}
	
	public List<ProductType> getAllTypes(){
		List<ProductType> types = new ArrayList<>();
		pTRepo.findAll().forEach(types::add);
		return types;
	}
	
	public ProductType findByDescription(String description) {
		return pTRepo.findByDescription(description);
	}
}
