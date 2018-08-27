package assignment3;
/*
 * Name: Seifullah Elharaki
 */
import stdlib.StdIn;
import java.util.*;

public class TestA3SimplerBST {
	public static A3SimplerBST<String, Integer> create_BST() {
		A3SimplerBST<String, Integer> ST = new A3SimplerBST<String, Integer>();
		A3SimplerBST<String, Integer> ST2 = new A3SimplerBST<String, Integer>();	
		String[] line = null;
		int count = 1;
		StdIn.fromFile("data/tale.txt");
		while (StdIn.hasNextLine()) {
			line = StdIn.readAllStrings();
				}
		for(int i = 0; i < line.length; i++) {
			if (ST.get(line[i]) ==null) ST.put(line[i], count);
			else ST.put(line[i], ST.get(line[i])+1);
		}
		int Longest = ST.longestPathLength();
		System.out.println("This is the Longest path in the BST for the Unsorted Array : " + Longest);
		System.out.println("Please be patient while the path for the Sorted Array is Calculated..");
		//Now Refilling a New BST After Sorting the Array
		Arrays.sort(line);
		for(int i = 0; i < line.length; i++) {
			if (ST2.get(line[i]) ==null) ST2.put(line[i], count);
			else ST2.put(line[i], ST2.get(line[i])+1);
		}
		int Longest2 = ST2.longestPathLength();
		System.out.println("This is the Longest path in the BST for the Sorted Array : " + Longest2);
		return ST;
	}
		
	public static void main(String[] args) {
		create_BST();
	}
}
