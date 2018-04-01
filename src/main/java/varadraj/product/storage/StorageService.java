package varadraj.product.storage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import varadraj.product.service.ImageService;


@Service
public class StorageService implements StorageServiceInterface{
	
	private final String uploadFolder;
	@Autowired
	private ImageService imageService;

	@Autowired
	public StorageService(StorageProperties storageProperties) {
		this.uploadFolder = storageProperties.getUploadFolder();
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(Paths.get(this.uploadFolder));
		}catch(IOException e) {
			System.out.println("UNABLE TO CREATE UPLOAD FOLDER");
		}
		
	}
	
	public String getFilename(MultipartFile file) {
		return StringUtils.cleanPath(file.getOriginalFilename());
	}
	
	public boolean isValidFile(MultipartFile file) {
		String filename = getFilename(file);
		if (file.isEmpty()) {
            System.out.println("FILE IS EMPTY. UNABLE TO SAVE " + filename);
            return false;
        }
		if (filename.contains("..")) { //Security check
            System.out.println("FILE SHOULD NOT CONTAIN '..'" + filename);
            return false;
        }
		return true;
	}

	@Override
	public boolean store(MultipartFile file,String fileDir) {
			String filename = getFilename(file);
	        try {	            
	            Path imagePath = Paths.get(this.uploadFolder,fileDir);
	            Files.createDirectories(imagePath);
	            Files.copy(file.getInputStream(), 
	            		imagePath.resolve(filename),
	                    StandardCopyOption.REPLACE_EXISTING);
	            return true;
	            
	        }
	        catch (IOException e) {
	            System.out.println("UNABLE TO SAVE FILE" + filename);
	            return false;
	        }
		
	}

	public Resource loadFile(long imageID) {
		try {
			String imageDir = new Long(imageID).toString();
			Path dirPath = Paths.get(this.uploadFolder,imageDir);
			String imageName = imageService.getImageName(imageID);
			Path imagePath = dirPath.resolve(imageName);
			Resource resource = new UrlResource(imagePath.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}

	
}