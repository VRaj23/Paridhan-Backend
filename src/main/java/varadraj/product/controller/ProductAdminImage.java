package varadraj.product.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import varadraj.common.model.JsonResponse;
import varadraj.common.model.ResponseMessage;
import varadraj.product.service.ImageService;
import varadraj.product.storage.StorageService;
import varadraj.product.util.ImageUtil;

@RestController
@CrossOrigin
@PreAuthorize(value = "hasRole('ADMIN')")
@RequestMapping("/auth/admin/product/image")
public class ProductAdminImage {
	
	@Autowired
	private StorageService storageService;
	@Autowired
	private ImageService imageService;
	
	@PostMapping("/upload")
	public JsonResponse<Void> handleFileUpload(@RequestParam("image") MultipartFile file,
			@RequestParam("ProductTypeID") String productTypeID){
		
		if(storageService.isValidFile(file) && ImageUtil.isImage(file)) {
			Long imageID = imageService.addImageReturnID(new Long(productTypeID),storageService.getFilename(file));
			try {
				storageService.storeImage(file, imageID.toString());
				return new JsonResponse<Void>(201
						, ResponseMessage.CREATED
						, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new JsonResponse<Void>(500
				, ResponseMessage.ERROR
				, null);
    }

}
