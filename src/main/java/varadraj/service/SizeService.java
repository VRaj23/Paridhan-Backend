package varadraj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.model.Size;
import varadraj.repository.SizeRepository;

@Service
public class SizeService {

	@Autowired
	private SizeRepository sizeRepo;
	
	public List<Size> getAllSize(){
		List<Size> sizes = new ArrayList<>();
		sizeRepo.findAll().forEach(sizes::add);
		return sizes;
	}
	
	public void populateData() {
		Size s = new Size("S",38);
		sizeRepo.save(s);
		
		Size m = new Size("M",40);
		sizeRepo.save(m);
		
		Size l = new Size("L",42);
		sizeRepo.save(l);
		
		Size xl = new Size("XL",44);
		sizeRepo.save(xl);
		
		Size xxl = new Size("XXL",46);
		sizeRepo.save(xxl);
		
		Size xxxl = new Size("XXXL",48);
		
		sizeRepo.save(xxxl);
		
	}
}
