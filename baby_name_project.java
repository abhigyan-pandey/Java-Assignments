import org.apache.commons.csv.* ;
import java.util.*;
import edu.duke.* ;
import java.io.* ;
public class baby_name_project
{
    public void totalBirths()
    {
            FileResource fr  = new FileResource() ;
            int total_birth = 0 ;
            int boys_counter = 0 ;
            int girls_counter = 0 ;

            for(CSVRecord record : fr.getCSVParser(false))
            {
                if(record.get(1).equals("F"))
                {
                    boys_counter++ ;
                }
                else
                {
                    girls_counter++;
                }
            }
            total_birth =boys_counter + girls_counter ;
            System.out.println("TOTAL BIRTHS ARE = " + total_birth);
            System.out.println("COUNT OF BOYS IN THE LIST IS =" +boys_counter);
            System.out.println("TOTAL COUNT OF GIRLS IS =" +girls_counter);
    }
    public int getRank(int year , String name ,String gender)
    {
        int rank = 0 ;
        FileResource fr = new FileResource("us_babynames/us_babynames_test/yob" + year + "short.csv" ) ;
        for(CSVRecord record : fr.getCSVParser(false))
        {
            if(record.get(1).equals(gender))
            {
                rank++ ;
                if(record.get(0).equals(name))
                {
                    return rank ;
                }
            }
        }
        return -1 ;
    }
    public String getName(int year , int rank , String gender)
    {
        int rank_checker = 0 ;
        FileResource fr = new FileResource("us_babynames/us_babynames_test/yob" + year + "short.csv" ) ;
        for(CSVRecord record : fr.getCSVParser(false))
        {
            if(record.get(1).equals(gender))
            {
                rank_checker++;
                if(rank_checker == rank)
                {
                    return record.get(0) ;
                }
            }
        }
        return "NO NAME";
    }
    public void whatIsNameInYear(String name , int year , int new_year , String gender)
    {
        int original_rank = getRank(year ,name , gender);
        String new_name = getName(new_year,original_rank,gender) ;
        System.out.println(name  + " born in " + year + " would be" + new_name + "if she was born in " +new_year );
    }
    public int yearOfHighestRnk(String name , String gender)
    {
        int max_rank = Integer.MAX_VALUE ;
        int year = Integer.MIN_VALUE ;
        DirectoryResource dr = new DirectoryResource() ;
        for(File f : dr.selectedFiles())
        {
            int current_year = Integer.parseInt(f.getName().substring(3, 7));
            int rank_value = getRank(current_year , name,gender) ;
            if(rank_value < max_rank && rank_value != -1)
            {
                max_rank = rank_value;
                year = current_year ;
            }
        }
        if(year == Integer.MIN_VALUE )
            return -1 ;
        else
            return year ;
    }
    public int getTotalBirthsRankedHigher(int year , String name , String gender)
    {
        int sum_of_births_ranked_higher = 0 ;
        FileResource fr = new FileResource("us_babynames/us_babynames_test/yob" + year + "short.csv") ;
        int rank_value = getRank(year , name , gender) ;
        if(rank_value == -1)
            return 0 ;
        else
        {
            int current_rank = 0 ;
            for(CSVRecord record : fr.getCSVParser(false))
            {
                if(record.get(1).equals(gender))
                {
                    if(current_rank < rank_value)
                    {
                        sum_of_births_ranked_higher += Integer.parseInt(record.get(2)) ;
                    }
                    else
                        break;
                }
            }
            return sum_of_births_ranked_higher ;
        }
    }
}
