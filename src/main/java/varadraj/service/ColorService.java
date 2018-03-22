package varadraj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.model.Color;
import varadraj.repository.ColorRepository;

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
		Color black = new Color("Black", 0,0,0);
		colorRepo.save(black);
		
		Color red = new Color("Red",255,0,0);
		colorRepo.save(red);
		
		Color blue = new Color("Blue",0,255,0);
		colorRepo.save(blue);
				
	}
}
