package varadraj.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.product.model.Brand;
import varadraj.product.repository.BrandRepository;

@Service
public class BrandService {
	
	@Autowired
	private BrandRepository brandRepo;
	
//CREATE
	public void addBrand(Brand brand) {
		brandRepo.save(brand);
	}
	
//READ
	public List<Brand> getAllBrand(){
		List<Brand> brands = new ArrayList<>();
		brandRepo.findAll().forEach(brands::add);
		return brands;
	}	
	
	public Brand findByName(String name) {
		return brandRepo.findByName(name);
	}
	
//UPDATE
	
//DELETE

}
