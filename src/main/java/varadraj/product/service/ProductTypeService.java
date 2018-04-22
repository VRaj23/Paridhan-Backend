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

//CREATE
	public void addProductType(ProductType productType) {
		pTRepo.save(productType);
	}

//READ
	public List<ProductType> getAllTypes(){
		List<ProductType> types = new ArrayList<>();
		pTRepo.findAll().forEach(types::add);
		return types;
	}
	
	public ProductType getType(Long typeID) {
		return pTRepo.findByTypeID(typeID);
	}
	
	public ProductType findByDescription(String description) {
		return pTRepo.findByDescription(description);
	}

	public ProductType findByTypeID(Long typeID) {
		return pTRepo.findByTypeID(typeID);
	}
	
//UPDATE
	public void updateProductType(ProductType oldType,ProductType newType) {
		if(newType.getDescription() == null)
			newType.setDescription(oldType.getDescription());
		if(newType.getTypeImage() == null)
			newType.setTypeImage(oldType.getTypeImage());
		
		pTRepo.save(newType);
		
	}
	
//DELETE
	public void deleteProductType(long productTypeID) {
		pTRepo.delete(this.findByTypeID(productTypeID));
	}
}
