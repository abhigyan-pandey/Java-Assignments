import java.util.*;

class part1 {

    private String findSimpleGene(String s) {
        String result = "";
        int index_atg = s.indexOf("ATG");
        int index_taa = s.indexOf("TAA");
        if (index_atg < 0)
            return result;
        else if (index_taa < 0)
            return result;
        else {
            if (index_atg < 0)
                return result;
            if (index_atg < index_taa)
                result = s.substring(index_atg + 3, index_taa);
            if (result.length() >= 3 && result.length() % 3 == 0)
                return result;
            else
                return "";
        }
    }

    private void testSimpleGene() {
        List<String> li = new ArrayList<>();
        System.out.println(li.size());

        li.add("ATGOPIUYTAA");
        li.add("ASDSDTAATG");
        li.add("PQWEARATGLKOPLMNBVTAA");
        li.add("ATGATAA");
        li.add("ATGPLOTAA");

        for (String x : li) {
            System.out.println("THE gene is " + findSimpleGene(x));
        }

    }
}

class part2 {
    private String findSimpleGene(String s, String start_codon, String stop_codon) {
        String result = "";
        String copy_string = "";
        int index_atg = s.indexOf(start_codon);
        int index_taa = s.indexOf(stop_codon);
        if (s.charAt(0) >= 65 && s.charAt(0) <= 90)
            copy_string = s.toUpperCase();
        else
            copy_string = s.toLowerCase();
        if (index_atg < 0)
            return result;
        else if (index_taa < 0)
            return result;
        else {
            if (index_atg < 0)
                return result;
            if (index_atg < index_taa)
                result = copy_string.substring(index_atg + 3, index_taa);
            if (result.length() >= 3 && result.length() % 3 == 0)
                return result;
            else
                return "";
        }
    }

    private void testSimpleGene() {
        List<String> li = new ArrayList<>();
        System.out.println(li.size());

        li.add("ATGOPIUYTAA");
        li.add("ASDSDTAATG");
        li.add("PQWEARATGLKOPLMNBVTAA");
        li.add("ATGATAA");
        li.add("ATGPLOTAA");

        for (String x : li) {
            System.out.println("THE gene is " + findSimpleGene(x, "ATG", "TAA"));
        }

    }
}

class part3 {
    private boolean twoOccurrences(String a, String b) {
        boolean flag = false;
        int occurence = 0;
        if (a.length() == 0 || b.length() == 0)
            return false;
        else if (a.length() >= b.length())
            return false;
        else {
            for (int i = 0; i <= b.length() - a.length(); i++) {
                for (int j = 0; j < b.length(); j++) {
                    if (a.charAt(j) != b.charAt(i)) {
                        flag = false;
                        break;
                    } else
                        flag = true;
                }
                if (flag)
                    occurence++;
                if (occurence == 2)
                    return true;
            }
            return false;
        }
    }

    private String lastPart(String a, String b) {
        boolean flag = false;
        int occurence = 0;
        if (a.length() == 0 || b.length() == 0)
            return b;
        else if (a.length() >= b.length())
            return b;
        else {
            int starting_index = 0;
            for (int i = 0; i <= b.length() - a.length(); i++) {
                for (int j = 0; j < b.length(); j++) {
                    if (a.charAt(j) != b.charAt(i)) {
                        flag = false;
                        break;
                    } else
                        flag = true;
                }
                if (flag)
                    occurence++;
                if (occurence == 1)
                    starting_index = i + a.length();
            }
            if (starting_index < b.length() - 1)
                return b.substring(starting_index, b.length());
            else
                return b;

        }
    }

    private void testLastPart() {
        List<String> li = new ArrayList<>();
        System.out.println("THE LAST PART WILL RETURN  " + lastPart("AN", "BANANA"));
        System.out.println("THE LAST PART WILL RETURN  " + lastPart("FOR", "FOREST"));
        System.out.println("THE LAST PART WILL RETURN  " + lastPart("AB", "ABHIGYAN"));
        System.out.println("THE LAST PART WILL RETURN  " + lastPart("ALOPQE", "ALOPQE"));
        System.out.println("THE LAST PART WILL RETURN  " + lastPart("HELLO", "FISHES"));

    }

}

class Part4 {
    public void findLinks(String url) {
        URLResource urlResource = new URLResource(url);

        for (String line : urlResource.lines()) {
            int youtubeIndex = line.toLowerCase().indexOf("youtube.com");

            if (youtubeIndex >= 0) {
                int startIndex = line.lastIndexOf("\"", youtubeIndex);
                int lastIndex = line.indexOf("\"", youtubeIndex);

                System.out.println("Youtube link: " + line.substring(startIndex + 1, lastIndex));
            }
        }
    }

    public void test() {
        String url = "http://www.dukelearntoprogram.com/course2/data/manylinks.html";
        findLinks(url);
    }

}