package varadraj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.model.ImageModel;
import varadraj.repository.ImageRepository;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepo;
	
	public Long addImageReturnID() {
		ImageModel image = new ImageModel();
		imageRepo.save(image);
		return image.getImageID();
	}
	
	public List<ImageModel> getAllImages(){
		List<ImageModel> images = new ArrayList<>();
		imageRepo.findAll().forEach(images::add);
		return images;
	}
}
