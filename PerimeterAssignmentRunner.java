import edu.duke.*;
import java.io.File;
import java.util.Iterator;

public class PerimeterAssignmentRunner {
    public double getPerimeter(Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints(Shape s) {
        return s.points.size();

    }

    public double getAverageLength(Shape s) {
        Iterator itr = s.points.iterator();
        while (itr.hasNext()) {
            double sum = 0.0;
            sum += Point.distance(itr.next());
        }
        double result = sum / getNumPoints(s);
        return result;
    }

    public double getLargestSide(Shape s) {
        Iterator itr = s.points.iterator();
        double largest_side = Double.MIN_VALUE;
        while (itr.hasNext()) {
            double current_side = Point.distance(itr.next());
            if (current_side > largest_side)
                largest_side = current_side;
        }
        return largest_side;
    }

    public double getLargestX(Shape s) {
        Iterator itr = s.points.iterator();
        double largest_X = Double.MIN_VALUE;
        while (itr.hasNext()) {
            double current_X = (double) Point.getX();
            if (current_X > largest_X)
                largest_X = current_X;
        }
        return largest_X;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largest_Perimeter = Double.MAX_VALUE;
        DirectoryResource dr = new DirectoryResource();
        File selections = dr.selectedFiles();
        for (File f : selections) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double curr_perimeter = getPerimeter(s);
            if (curr_perimeter > largest_Perimeter)
                largest_Perimeter = curr_perimeter;
            return largest_Perimeter;
        }
    }

    public File getFileWithLargestPerimeter() {
        File file_with_largest_Perimeter;
        DirectoryResource dr = new DirectoryResource();
        File selections = dr.selectedFiles();
        for (File f : selections) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double curr_perimeter = getPerimeter(s);
            if (curr_perimeter > largest_Perimeter) {
                largest_Perimeter = curr_perimeter;
                file_with_largest_Perimeter = f;
            }
            return file_with_largest_Perimeter;
        }
    }

    public void testPerimeter() {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int count_of_points = getAverageLength(s);
        System.out.println("Count of Points = " + count_of_points);
        double average_length = getAverageLength(s);
        System.out.println("The average length is " + average_length);
        double longest_side = getLargestSide(s);
        System.out.println("The largest side is = " + longest_side);
        double largest_x = getLargestX(s);
        System.out.println("The largest x value is " + largest_x);
        System.out.println("perimeter = " + length);
    }

    public void testPerimeterMultipleFiles() {
        double largest_Perimeter = getLargestPerimeterMultipleFiles();
        return largest_Perimeter;
    }

    public void testFileWithLargestPerimeter() {
        File file_name = getFileWithLargestPerimeter();
        System.out.println("The file name is " + file_name.getName());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle() {
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0, 0));
        triangle.addPoint(new Point(6, 0));
        triangle.addPoint(new Point(3, 6));
        for (Point p : triangle.getPoints()) {
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = " + peri);
    }

    // This method prints names of all files in a chosen folder that you can use to
    // test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main(String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
