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
	
	public List<Color> getAllColor(){
		List<Color> colors = new ArrayList<>();
		colorRepo.findAll().forEach(colors::add);
		return colors;
	}
	
	public void addColorDummyData() {
		Color black = new Color("Black","000000");
		colorRepo.save(black);
		
		Color red = new Color("Red","FF0000");
		colorRepo.save(red);
		
		Color blue = new Color("Blue","0000FF");
		colorRepo.save(blue);
		
		Color white = new Color("White","FFFFFF");
		colorRepo.save(white);
		
		Color green = new Color("Green","00FF00");
		colorRepo.save(green);
		
		Color yellow = new Color("Yellow","FFFF00");
		colorRepo.save(yellow);
				
	}
	
	public Color findByName(String name) {
		return colorRepo.findByName(name);
	}
}
