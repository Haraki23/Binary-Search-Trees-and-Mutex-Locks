package assignment1;
import algs31.*;
import stdlib.*;
public class ComputeARR {

public static String[] readfile() {
	String [] stars = null;
	StdIn.fromFile("data/a1ratings.txt");
	while (StdIn.hasNextLine()) {
		stars = StdIn.readAllStrings();
	}
	return stars;
}
	
public static void main(String[] args) {
	double average = 0;
	double accumulator = 0;
	int i;
	String [] rating = null;
	BinarySearchST<String, Double> ST = new BinarySearchST<String, Double>();
	ST.put("********", 4.0);
	ST.put("*******", 3.5);
	ST.put("******", 3.0);
	ST.put("*****", 2.5);
	ST.put("****", 2.0);
	ST.put("***", 1.5);
	ST.put("**", 1.0);
	ST.put("*", 0.5);
	rating = readfile();
	for (i=0; i < rating.length; i++ ) {
		accumulator += ST.get(rating[i]);
	}
	average = accumulator/(rating.length);
	System.out.println("The current average restaurant rating is: " + average);
}
}
