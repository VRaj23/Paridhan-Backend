package varadraj.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService implements StorageServiceInterface{
	
	private final Path uploadedFileStorageLocation;

	@Autowired
	public StorageService(StorageProperties storageProperties) {
		this.uploadedFileStorageLocation = Paths.get(storageProperties.getUploadFolder()); 
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(uploadedFileStorageLocation);
		}catch(IOException e) {
			System.out.println("UNABLE TO CREATE UPLOAD FOLDER");
		}
		
	}

	@Override
	public void store(MultipartFile file) {
		 String filename = StringUtils.cleanPath(file.getOriginalFilename());
	        try {
	            if (file.isEmpty()) {
	                System.out.println("FILE IS EMPTY. UNABLE TO SAVE " + filename);
	            }
	            if (filename.contains("..")) {
	                //Security check
	                System.out.println(
	                        "FILE SHOULD NOT CONTAIN '..'"
	                                + filename);
	            }
	            Files.copy(file.getInputStream(), this.uploadedFileStorageLocation.resolve(filename),
	                    StandardCopyOption.REPLACE_EXISTING);
	        }
	        catch (IOException e) {
	            System.out.println("UNABLE TO SAVE FILE" + filename);
	        }
		
	}

}
