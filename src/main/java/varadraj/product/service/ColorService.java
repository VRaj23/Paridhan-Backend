package varadraj.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.product.model.Color;
import varadraj.product.repository.ColorRepository;

@Service
public class ColorService {
	
	@Autowired
	private ColorRepository colorRepo;
	
//CREATE
	public void addColor(Color color) {
		colorRepo.save(color);
	}
	
//READ
	public List<Color> getAllColor(){
		List<Color> colors = new ArrayList<>();
		colorRepo.findAll().forEach(colors::add);
		return colors;
	}	
	
	public Color findByColorID(long colorID) {
		return colorRepo.findByColorID(colorID);
	}
	
	public Color findByColorValue(String value) {
		return colorRepo.findByValue(value);
	}
	
//UPDATE
	
//DELETE
	public void deleteColor(Color color) {
		colorRepo.delete(color);
	}

}
