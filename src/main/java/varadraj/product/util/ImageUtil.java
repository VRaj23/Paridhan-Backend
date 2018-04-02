package varadraj.product.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;


public class ImageUtil {
	
	
	public static BufferedImage ResizeImage(File image) throws IOException {
		BufferedImage original = ImageIO.read(image);
        BufferedImage resize = new BufferedImage(385,480,original.getType());
        Graphics2D g2d = resize.createGraphics();
        g2d.drawImage(original,0,0,385,480,null);
        g2d.dispose();
        return resize;
	}


	public static boolean isImage(MultipartFile file) {
		String mimetype = file.getContentType();
		if(mimetype == null)
			return false;
		else if(mimetype.startsWith("image")) {
			return true;
		}
		else {
			System.out.println("File of type "+mimetype+" is not an image");
			return false;
		}
	}
}
