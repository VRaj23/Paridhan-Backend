package varadraj.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.product.model.ProductType;
import varadraj.product.model.Size;
import varadraj.product.repository.SizeRepository;

@Service
public class SizeService {

	@Autowired
	private SizeRepository sizeRepo;
	@Autowired
	private ProductTypeService typeService;
	
	public List<Size> getAllSize(){
		List<Size> sizes = new ArrayList<>();
		sizeRepo.findAll().forEach(sizes::add);
		return sizes;
	}
	
	public Size findSize(String sizeChar) {
		return sizeRepo.findBySizeCharacter(sizeChar);
	}
	
	public void addDummySizeData() {
		ProductType shirt = typeService.findByDescription("Shirt");
		
		Size s = new Size("S",38,shirt);
		sizeRepo.save(s);
		
		Size m = new Size("M",40,shirt);
		sizeRepo.save(m);
		
		Size l = new Size("L",42,shirt);
		sizeRepo.save(l);
		
		Size xl = new Size("XL",44,shirt);
		sizeRepo.save(xl);
		
		Size xxl = new Size("XXL",46,shirt);
		sizeRepo.save(xxl);
		
		Size xxxl = new Size("XXXL",48,shirt);
		
		sizeRepo.save(xxxl);
		
	}
}
