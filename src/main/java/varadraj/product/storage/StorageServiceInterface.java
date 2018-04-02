package varadraj.product.storage;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface StorageServiceInterface {
	
    void init();

    void storeImage(MultipartFile file, String imageDir) throws IOException;

}
