package varadraj.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageServiceInterface {
	
    void init();

    void store(MultipartFile file);

}
