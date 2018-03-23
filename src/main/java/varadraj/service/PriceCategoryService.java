package varadraj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.model.PriceCategory;
import varadraj.repository.PriceCategoryRepository;

@Service
public class PriceCategoryService {

	@Autowired
	private PriceCategoryRepository pcRepo;
	
	public List<PriceCategory> getAllPriceCategory(){
		List<PriceCategory> categories = new ArrayList<>();
		pcRepo.findAll().forEach(categories::add);
		return categories;
	}
	
	public void addDummyPriceCategoryData() {
		PriceCategory pc1 = new PriceCategory(0, 499);
		pcRepo.save(pc1);
		PriceCategory pc2 = new PriceCategory(500, 999);
		pcRepo.save(pc2);
	}
	
	public PriceCategory findByUpperLimit(int upperLimit) {
		return pcRepo.findByUpperLimit(upperLimit);
	}
}
