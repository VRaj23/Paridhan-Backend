package varadraj.product.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageServiceInterface {
	
    void init();

    boolean store(MultipartFile file, String imageDir);

}
