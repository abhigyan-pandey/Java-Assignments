import java.util.*;
import edu.duke.* ;
import java.io.* ;
public class image_inversion
{
    public ImageResource makeInversion(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage);

        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int r = inPixel.getRed();
            int g = inPixel.getGreen();
            int b = inPixel.getBlue();

            pixel.setRed(255 - r);
            pixel.setGreen(255 - g);
            pixel.setBlue(255 - b);
        }

        return outImage;
    }

    public void save_and_Convert() {
        DirectoryResource dr = new DirectoryResource();

        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource outImage = makeInversion(inImage);

            String fileName = inImage.getFileName();
            String newFileName = "inverted-" + fileName;

            outImage.setFileName(newFileName);
            outImage.save();
        }
    }
}
