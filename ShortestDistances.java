package assignment6;
import algs41.BreadthFirstPaths;
import algs41.Graph;
import assignment4.A4GeneticSequences;
import algs31.*;
import algs32.*;
import algs33.*;
import week1examples.*;
import week3examples.*;
import stdlib.*;
import java.util.*;
import algs13.*;
import algs13.Queue;
/* Name: Seifullah Elharaki*/
public class ShortestDistances {

public static void main(String[] args) {
	RedBlackBST<String, Integer> ST = new RedBlackBST<>();
	StdIn.fromFile("data/a6cities.txt");
	int i;
	int j = 0;
	int z;
	int count =0;
	String[] fields;
	String[] fields2;
	// Adding in the cities to the ST
	while (StdIn.hasNextLine()) {
		String line = StdIn.readLine();
		fields = line.split("\\t+");
		for(i=0; i < fields.length; i++) {
			ST.put(fields[0],j);
			j++;
		}
		}
	//Defining Graph Size & Edges
	Graph graph = new Graph(ST.size());
	String cities [] = {"Chicago", "KansasCity", "Minneapolis", "Wausau", "LaCrosse"};
	String cities2 [] = {"Chicago", "KansasCity", "Minneapolis", "Wausau", "LaCrosse"};
	StdIn.fromFile("data/a6connections.txt");
	while (StdIn.hasNextLine()) {
		String line2 = StdIn.readLine();
		fields2 = line2.split("\\s+");
	    graph.addEdge(ST.get(fields2[0]), ST.get(fields2[1]));
	}
	BreadthFirstPaths BFS = new BreadthFirstPaths(graph, ST.get("Chicago"));
	System.out.println("               Chicago        KansasCity     Minneapolis    Wausau         LaCrosse");
	//Iterate through outer array of cities
	for (int c = 0 ; c < cities.length; c++) {
		if (c==0) 		StdOut.format("%-11s",cities[c]);
		else {System.out.println("\t");
		StdOut.format("%-11s",cities[c]);}
	//Iterate through the inner array of cities
	for (int d = 0 ; d < cities.length; d++) {
	if (cities[c] == "Chicago") {
	StdOut.format("%15d", BFS.distTo(ST.get(cities[d])));
	count++;
	}
	else
	StdOut.format("%15d", (BFS.distTo(ST.get(cities[d]))+BFS.distTo(ST.get(cities[c]))));
	}
	}
}
}
