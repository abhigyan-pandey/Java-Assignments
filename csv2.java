import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
import java.util.*;

public class csv2
{
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestRecord = null;

        for (CSVRecord record : parser) {
            double temp = Double.parseDouble(record.get("TemperatureF"));

            if (coldestRecord == null && temp != -9999) {
                coldestRecord = record;
            } else {
                double coldestTemperature = Double.parseDouble(coldestRecord.get("TemperatureF"));

                if (temp < coldestTemperature && temp != -9999) {
                    coldestRecord = record;
                }
            }
        }
        return coldestRecord;
    }
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord coldestRecord = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + coldestRecord.get("TemperatureF") + " at " + coldestRecord.get("DateUTC"));
    }
    public File fileWithColdestTemperature()
    {
        DirectoryResource dr = new DirectoryResource() ;
        File filer = null ;
        CSVRecord coldestrecord = null ;
        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f) ;
            CSVRecord current = coldestHourInFile(fr.getCSVParser()) ;
            if (coldestrecord == null) {
                coldestrecord = current;
                filer = f;
            } else {
                double coldestTemperature = Double.parseDouble(coldestrecord.get("TemperatureF"));
                double currentTemperature = Double.parseDouble(current.get("TemperatureF"));

                if (currentTemperature < coldestTemperature) {
                    coldestrecord = current;
                    filer = f;
                }
            }
        }
        return filer ;
    }
    public void printAllRecordsInFile(CSVParser parser) {
        for (CSVRecord record : parser) {
            System.out.println(record.get("DateUTC") + " " + record.get("TemperatureF"));
        }
    }

    public void testFileWithColdestTemperature()
    {
    File file = fileWithColdestTemperature();
    System.out.println("Coldest day was in file " + file.getName());

    FileResource fr = new FileResource(file);
    String coldestTemperature = coldestHourInFile(fr.getCSVParser()).get("TemperatureF");
    System.out.println("Coldest temperature that day was " + coldestTemperature);

    System.out.println("All the Temperatures on the coldest day were:");
    printAllRecordsInFile(fr.getCSVParser());
}

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumidityRecord = null;

        for (CSVRecord record : parser) {
            String humidityStr = record.get("Humidity");

            if (!humidityStr.equals("N/A")) {
                int humidity = Integer.parseInt(humidityStr);

                if (lowestHumidityRecord == null) {
                    lowestHumidityRecord = record;
                } else {
                    int lowestHumidity = Integer.parseInt(lowestHumidityRecord.get("Humidity"));

                    if (humidity < lowestHumidity) {
                        lowestHumidityRecord = record;
                    }
                }
            }
        }

        return lowestHumidityRecord;
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestHumidityRecord = lowestHumidityInFile(parser);

        System.out.println("Lowest Humidity was " + lowestHumidityRecord.get("Humidity") + " at " + lowestHumidityRecord.get("DateUTC"));
    }


    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumidityRecord = null;

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRecord = lowestHumidityInFile(fr.getCSVParser());

            if (lowestHumidityRecord == null) {
                lowestHumidityRecord = currentRecord;
            } else {
                int lowestHumidity = Integer.parseInt(lowestHumidityRecord.get("Humidity"));
                int currentHumidity = Integer.parseInt(currentRecord.get("Humidity"));

                if (currentHumidity < lowestHumidity) {
                    lowestHumidityRecord = currentRecord;
                }
            }
        }

        return lowestHumidityRecord;
    }

    public void testLowestHumidityInManyFiles() {
        CSVRecord lowestHumidityRecord = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowestHumidityRecord.get("Humidity") + " at " + lowestHumidityRecord.get("DateUTC"));
    }


    public double averageTemperatureInFile(CSVParser parser) {
        double totalTemperature = 0;
        int recordCount = 0;

        for (CSVRecord record : parser) {
            double currentTemperature = Double.parseDouble(record.get("TemperatureF"));

            if (currentTemperature != -9999) {
                totalTemperature += currentTemperature;
                recordCount++;
            }

        }

        return totalTemperature / recordCount;
    }


    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        double averageTemperature = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is " + averageTemperature);
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double totalTemperature = 0;
        int recordCount = 0;

        for (CSVRecord record : parser) {
            int currentHumidity = Integer.parseInt(record.get("Humidity"));

            if (currentHumidity >= value) {
                double currentTemperature = Double.parseDouble(record.get("TemperatureF"));

                if (currentTemperature != -9999) {
                    totalTemperature += currentTemperature;
                    recordCount++;
                }
            }
        }

        if (recordCount == 0) {
            return Double.NEGATIVE_INFINITY;
        } else {
            return totalTemperature / recordCount;
        }
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        double averageTemperature = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);

        if (averageTemperature == Double.NEGATIVE_INFINITY) {
            System.out.println("No temperature with that humidity");
        } else {
            System.out.println("Average temperature when high humidity is " + averageTemperature);
        }
    }
}
