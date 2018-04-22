package varadraj.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.product.model.Size;
import varadraj.product.repository.SizeRepository;

@Service
public class SizeService {

	@Autowired
	private SizeRepository sizeRepo;
	
//CREATE
	public void addSize(Size size) {
		sizeRepo.save(size);
	}
	
//READ	
	public List<Size> getAllSize(){
		List<Size> sizes = new ArrayList<>();
		sizeRepo.findAll().forEach(sizes::add);
		return sizes;
	}
	
	public Size findSize(String sizeChar) {
		return sizeRepo.findBySizeCharacter(sizeChar);
	}
	
//UPDATE
	
//DELETE
	
}
