package varadraj.product.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import varadraj.model.JsonResponse;
import varadraj.product.service.ImageService;
import varadraj.product.storage.StorageService;
import varadraj.product.util.ImageUtil;

@RestController
@RequestMapping("/product/admin/image")
public class ProductAdminImage {
	
	@Autowired
	private StorageService storageService;
	@Autowired
	private ImageService imageService;
	
	@PostMapping("/upload")
	public JsonResponse handleFileUpload(@RequestParam("image") MultipartFile file,
			@RequestParam("ProductTypeID") String productTypeID){
		
		if(storageService.isValidFile(file) && ImageUtil.isImage(file)) {
			Long imageID = imageService.addImageReturnID(new Long(productTypeID),storageService.getFilename(file));
			try {
				storageService.storeImage(file, imageID.toString());
				return new JsonResponse(201,"Image Uploaded Successfully");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new JsonResponse(500,"Image could not be saved");
    }

}
