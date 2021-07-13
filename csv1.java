import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
import java.util.*;

class csv1
{
    public String countryInfo(CSVParser parser ,String country)
    {
        for(CSVRecord record : parser)
        {
            String present_country = record.get("Country") ;
            if(country.equals(present_country))
            {
                String result = present_country + ":"  + record.get("Exports") +":" + record.get("Value (dollars)");
                return result ;
            }
        }
        return "NOT FOUND" ;
    }
    void listExportersTwoProducts(CSVParser parser , String expi1 , String expi2)
    {
        for(CSVRecord record : parser)
        {
            String export_item_string = record.get("Exports");
            if(export_item_string.contains(expi1) && export_item_string.contains(expi2))
            {
                System.out.println("The country will be -> " + record.get("Country"));
            }
        }
    }
    int numberOfExporters(CSVParser parser , String export_item)
    {
        int count_of_exporters = 0 ;
        for(CSVRecord record : parser)
        {
            String Export_String = record.get("Exports");
            if(Export_String.contains(export_item))
            {
                count_of_exporters++ ;
            }
        }
        return count_of_exporters ;
    }

    void bigExporters(CSVParser parser , String amount)
    {
        for(CSVRecord record : parser)
        {
                if(record.get("Value (dollars)").length() > amount.length())
                {
                    System.out.println( record.get("Country") + " " + record.get("Value (dollars)"));
                }
        }
    }
}
