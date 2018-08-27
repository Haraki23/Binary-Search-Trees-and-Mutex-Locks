package assignment5;
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

public class A5ReorganizingBST<K extends Comparable<K>, V> {
	private SimplerBST<K, V> bstree;
	private int Key_Nums = 0;
	public A5ReorganizingBST() {
		//Constructor
		bstree = new SimplerBST<>();
	}
	
	public V get(K key) {
		if (bstree.get(key) == null) return null;
		else return bstree.get(key);
	}
	
	public void put(K key, V value) {
		if (bstree.get(key) == null){
			Key_Nums++;
			bstree.put(key, value);
		}
		else bstree.put(key, value);
		if (Key_Nums % 100 == 0) {
			reorganize();
		}
	}
	
	public void delete(K key) {
		if (bstree.get(key) == null) return;
		else {
			Key_Nums--;
			bstree.delete(key);
		}
		if (Key_Nums % 100 == 0) {
			reorganize();
		}
	}
	public int height() {
		return bstree.height();
		
	}
	private Iterable<K> listByMedians(List<K> keys) {
		algs13.Queue<K> medianQueue = new algs13.Queue<>();
		algs13.Queue<Integer> queueLeft = new algs13.Queue<>();
		algs13.Queue<Integer> queueRight = new algs13.Queue<>();
		queueLeft.enqueue(0);
		queueRight.enqueue(keys.size()-1);
		while (!queueLeft.isEmpty()) {
			int left = queueLeft.dequeue();
			int right = queueRight.dequeue();
			if (left <= right) {
				int medianIndex = (right+left)/2;
				K median = keys.get(medianIndex);
				medianQueue.enqueue(median);
				queueLeft.enqueue(left);
				queueRight.enqueue(medianIndex-1);
				queueLeft.enqueue(medianIndex+1);
				queueRight.enqueue(right);
			}
		}
		return medianQueue;
	}

	private void reorganize() {
		int i;
		SimplerBST<K, V> New_bstree = new SimplerBST<>();
		ArrayList <K> Sorted_fields = new ArrayList <K> ();
		for (K key: bstree.keys()) Sorted_fields.add(key);
		for (K obj: listByMedians(Sorted_fields)) {
			New_bstree.put(obj, bstree.get(obj));
		}
		//Reassign
		bstree = New_bstree;

	}

}

