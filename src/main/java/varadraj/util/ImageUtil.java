package varadraj.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

public class ImageUtil {

	public static void ResizeImage(File image) throws IOException {
		BufferedImage bImage = ImageIO.read(image);
	}

	public static boolean fileIsNotImage(MultipartFile file) {
		String mimetype = file.getContentType();
		if(mimetype == null)
			return true;
		else if(mimetype.startsWith("image")) {
			return false;
		}
		else {
			System.out.println("File of type "+mimetype+" is not an image");
			return true;
		}
	}
}
