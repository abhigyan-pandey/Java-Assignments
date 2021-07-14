import org.apache.commons.csv.* ;

import java.awt.*;
import java.util.*;
import edu.duke.* ;
import java.io.* ;
public class batch_grayscale
{
    public ImageResource create_greyscale(ImageResource original_img)
    {
        ImageResource resultant_img = new ImageResource() ;
       for(Pixel pixel : original_img.pixels())
       {
           int red_value = pixel.getRed();
           int green_value = pixel.getGreen();
           int blue_value = pixel.getBlue() ;

           int average_value = (red_value + green_value + blue_value) / 3 ;
           pixel.setRed(average_value) ;
           pixel.setGreen(average_value);
           pixel.setBlue(average_value);
       }
    return resultant_img ;
    }

    void save_and_display(ImageResource output_image)
    {
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            ImageResource inImage = new ImageResource(f) ;
            ImageResource outImage = create_greyscale(inImage) ;
            String file_name = inImage.getFileName();
            String output_file_name = "gray-" + file_name ;
            outImage.setFileName(output_file_name) ;
            outImage.save();
        }
    }
}
