package assignment4;
import java.util.ArrayList;

import stdlib.StdIn;
import stdlib.StdOut;
/* Name: Seifullah Elharaki*/

public class A4TestGeneticSequences {
	//Method to read the file
	public static void readfile(A4GeneticSequences x) {
		StdIn.fromFile("data/sequences.txt");
		int i;
		String[] fields;
		while (StdIn.hasNextLine()) {
			String line = StdIn.readLine();
			fields = line.split("\\t+");
			for(i=0; i < fields.length; i++) x.addSpecies(fields[0], fields[1]);
		}
		}
	//Method to Print Species
	public static void printSpecies(ArrayList <String> Sorted_fields, A4GeneticSequences x) {
		String Seq;
		int j = 0;
		//To add all the species
		for (j = 0 ; j < Sorted_fields.size(); j++) {
		Seq = x.sequence(Sorted_fields.get(j));
		StdOut.println("Species : " + Sorted_fields.get(j) + " Sequence : " + Seq.substring(0, 20) );
	}
	}
	//Method to Remove All Species
	public static void removeAllSpecies(A4GeneticSequences x) {
		StdOut.println("Deleting All Species.....");
		//To Remove all the Species
		for (String key: x.collection.keys()){
		System.out.print(key + " <-Deleted-> ");
		x.removeSpecies(key);
		}
		}
	public static void main(String[] args) {
		A4GeneticSequences Test = new A4GeneticSequences();
		//Read the File
		readfile(Test);
		//Print the Current Size22 of Species
		System.out.println("The Current Number Of Species is: " + Test.size());
		//Print the Species in Alphabetical Order where, specieslIST() Returns an ArrayList
		printSpecies(Test.speciesList(), Test);
		//Deletes all the Species
		removeAllSpecies(Test);
		//Print Current Amount of Species
		System.out.println(); //New Line
		System.out.println("The Current Number Of Species is: " + Test.size());
		}
}