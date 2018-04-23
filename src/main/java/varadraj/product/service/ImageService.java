package varadraj.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import varadraj.product.model.ImageModel;
import varadraj.product.repository.ImageRepository;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepo;
	@Autowired
	private ProductTypeService productTypeService;

//CREATE
	public Long addImageReturnID(Long imageTypeID, String filename) {
		System.out.println("adding "+filename+" to Database "+imageTypeID.toString());
		ImageModel image = new ImageModel(productTypeService.getType(imageTypeID),filename);
		imageRepo.save(image);
		return image.getImageID();
	}
//READ
	public List<ImageModel> getAllImages(){
		List<ImageModel> images = new ArrayList<>();
		imageRepo.findAll().forEach(images::add);
		return images;
	}
	
	public ImageModel findByImageID(long imageID) {
		return imageRepo.findByImageID(imageID);
	}
	
	public String getImageName(long imageID) {
		return this.findByImageID(imageID).getName();
	}
	
//UPDATE

//DELETE
}
