package assignment8;
import algs42.Digraph;
import algs42.DirectedCycle;
import algs42.DirectedDFS;
import stdlib.*;
import java.util.*;
import stdlib.*;
import algs33.RedBlackBST;
/* Name: Seifullah Elharaki*/

public class IndependentCourses {

//Initialize
static RedBlackBST <String, Integer> BST_Track = new RedBlackBST<>(); // This will hold (Course, Course ID#)
static ArrayList <String> Courses = new ArrayList<>(); //Will just hold a list of Courses

//This will read in the sequence of course names
public static void readCourses() {
	System.out.println("Reading in course list....");
	StdIn.fromFile("data/a8courses.txt");
	String[] fields;
	int i =0;
	while (StdIn.hasNextLine()) {
		String line = StdIn.readLine();
		fields = line.split("\\t+");
		for(i=0; i < fields.length; i++) 
			if (Courses.contains(fields[i]) == false)
				Courses.add(fields[i]); // If its not already in there add our course to the Array list 
}
	
}

public static void readPreqs() {
	System.out.println("Adding preqs to BST_PReq....");
	StdIn.fromFile("data/a8prereqs.txt");
	String[] fields;
	while (StdIn.hasNextLine()) {
		String line = StdIn.readLine();
		fields = line.split("\\s+");		
		// We will check that all the courses in the preReq Column are added to the ArrayList
		if (Courses.contains(fields[0]) == false ) { //If we dont already have the course in the list add it
			Courses.add(fields[0]);
			}
		// We will check that all the courses in the Course Column are added to the ArrayList
		if (Courses.contains(fields[1]) == false ) { //If we dont already have the course in the list add it
			Courses.add(fields[1]);
					}
		}
		}

public static void build_Course_ID() {
	int Course_ID = 0;
	//Build our Course_ID Symbol Table
		System.out.println("Building Course_ID Symbol Table....");
		for (String course: Courses) {
			BST_Track.put(course, Course_ID);
			Course_ID++;
		}
}

public static void build_Digraph(Digraph Digraph) {
Integer Course_ID = null;
Integer Preq_ID = null;
StdIn.fromFile("data/a8prereqs.txt");
String[] fields;
//Iterate through our course list
System.out.println("Building Digraph....");
//You want to say that if it does in fact contain the course add the preq in the Digraph
while (StdIn.hasNextLine()) {
String line = StdIn.readLine();
fields = line.split("\\s+");
	if(Courses.contains(fields[1]) == true) { // If that course is already in our Courses List
		Preq_ID = BST_Track.get(fields[0]);
		Course_ID = BST_Track.get(fields[1]);
		//System.out.println(Course_ID + "      " + Preq_ID);
		Digraph.addEdge(Preq_ID,Course_ID);
}
}

}

public static boolean checkDAG(DirectedCycle test) {
	
	return test.hasCycle();
}


public static void readIndependant(Digraph digraph) {
Integer Course_ID_0 = null;
Integer Course_ID_1 = null;
boolean condition = false;
StdIn.fromFile("data/a8independence.txt");
String[] fields;
//Iterate through our course list
//System.out.println("Checking Independance....");
//You want to say that if it does in fact contain the course add the preq in the Digraph
while (StdIn.hasNextLine()) {
String line = StdIn.readLine();
fields = line.split("\\s+");
Course_ID_0 = BST_Track.get(fields[0]);
Course_ID_1 = BST_Track.get(fields[1]);
boolean one = false, two = false, three = false;
DirectedDFS DFS = new DirectedDFS(digraph, Course_ID_0);
	if(DFS.marked(Course_ID_1) == true) {
		one = true;
		}
	else if (DFS.marked(Course_ID_1) == false) {
		DirectedDFS DFS2 = new DirectedDFS(digraph, Course_ID_1);
		if(DFS2.marked(Course_ID_0) == true) {
			two = true;
		}
		else if (DFS2.marked(Course_ID_0) == false) {
			three = true;
		}
	}
	if (one == true) System.out.println(fields[0]+" is a prerequisite of "+fields[1]);
	else if (two == true) System.out.println(fields[1]+" is a prerequisite of "+fields[0]);
	else if (three == true) System.out.println(fields[0]+ " " + fields[1]+ " are independant");
	System.out.println("----------------------------------");
}}                        
	
	
public static void main(String[] args) {
readCourses();
readPreqs();
build_Course_ID();
//Create the Digraph
Digraph digraph = new Digraph(BST_Track.size());
//If it's a DAG or Not
boolean is_DAG = false;
//Pass in the Digraph and Build it
build_Digraph(digraph);
DirectedCycle DAG_Check = new DirectedCycle(digraph);
is_DAG = checkDAG(DAG_Check);
System.out.println("==============================");
System.out.println("The Course List is as Follows:");
System.out.println("==============================");
for (String course: Courses) {
	System.out.println(course);
}
System.out.println("==================================");
if (is_DAG != false) {
	System.out.println("It is not a DAG, cycle found.. Terminating...");
	return;
}

else readIndependant(digraph);

}


}


