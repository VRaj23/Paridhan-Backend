package varadraj.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.product.model.PriceCategory;
import varadraj.product.repository.PriceCategoryRepository;

@Service
public class PriceCategoryService {

	@Autowired
	private PriceCategoryRepository pcRepo;

//CREATE
	public void addPriceCategory(PriceCategory priceCategory) {
		pcRepo.save(priceCategory);
	}
	
//READ
	public List<PriceCategory> getAllPriceCategory(){
		List<PriceCategory> categories = new ArrayList<>();
		pcRepo.findAll().forEach(categories::add);
		return categories;
	}
	
	public PriceCategory findByUpperLimit(int upperLimit) {
		return pcRepo.findByUpperLimit(upperLimit);
	}
	
//UPDATE
	
//DELETE	
}
