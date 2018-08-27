package assignment4;
import algs31.*;
import algs32.*;
import algs33.*;
import stdlib.*;
import java.util.*;
/* Name: Seifullah Elharaki*/

	public class A4GeneticSequences {
	RedBlackBST<String, String> collection;
	//Constructor to create empty collection
	A4GeneticSequences(){
		collection = new RedBlackBST<>();
	}
	//Add Species Method
	public void addSpecies(String species, String sequence) {
		collection.put(species, sequence);
	}
	//Remove Species Method
	public void removeSpecies(String species) {
		collection.delete(species);
	}
	//Get Genetic Sequence Method
	public String sequence(String species) {
		if (collection.get(species) == null) return null;
		else return collection.get(species);
	}
	//Get Size of Collection Method
	public int size() {
		return (collection.size());
	}
	//Method that returns the ArrayList
	public ArrayList <String> speciesList() {
		ArrayList <String> Sorted_fields = new ArrayList <String> ();
		//Add the keys to an Array that well Sort
		for (String key: collection.keys()) Sorted_fields.add(key);
		//Sorting the array to output alphabetically
		return Sorted_fields;
	}
}
