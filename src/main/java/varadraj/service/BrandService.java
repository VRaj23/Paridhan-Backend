package varadraj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.model.Brand;
import varadraj.repository.BrandRepository;

@Service
public class BrandService {
	
	@Autowired
	private BrandRepository brandRepo;
	
	public List<Brand> getAllBrand(){
		List<Brand> brands = new ArrayList<>();
		brandRepo.findAll().forEach(brands::add);
		return brands;
	}
	
	public void addDummyBrandData() {
		Brand spykar = new Brand("Spykar");
		brandRepo.save(spykar);
		Brand polo = new Brand("Polo");
		brandRepo.save(polo);
		Brand raymonds = new Brand("Raymonds");
		brandRepo.save(raymonds);
	}

}
