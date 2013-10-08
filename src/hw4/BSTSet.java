package hw4;

/**
 * Implementation of a set using a binary search tree, which gives us O(log n)
 * performance for the add, remove, and contains operations.  These operations
 * are done via delegation to the BinarySearchTree class we wrote earlier.
 * 
 */

import java.util.Iterator;

public class BSTSet<E extends Comparable<E>>
{
	private BinarySearchTree<E> data = new BinarySearchTree<E>();
	
	// Adds the specified newItem to the set.  Takes no action if the
	//  newItem is already a member of the set.
	public void add(E newItem)
	{
		data.add(newItem);
	}

	// Removes the specified someItem from the set.
	public void remove(E someItem)
	{
		data.delete(someItem);
	}
	
	// Returns whether the specified someItem is a member of the set.
	public boolean contains(E someItem)
	{
		return (data.find(someItem) != null);
	}

	// Returns an iterator over the set elements (this will be an in-order
	//  iterator, since that's how we wrote the iterator() method in BST).
	public Iterator<E> iterator()
	{
		return data.iterator();
	}
	
	public String toString()
	{
		String r = "Elements in set: {";
		Iterator<E> it = this.iterator();
		while (it.hasNext())
			r += it.next() + ", ";
		r = r.substring(0, r.length() - 2);	// remove last comma and space
		r += "}";
		return r;
	}
	
	public static void main(String[] args)
	{
		BSTSet<String> set = new BSTSet<String>();
		set.add("Donatello");
		set.add("Raphael");
		set.add("Leonardo");
		set.add("Michaelangelo");
		set.add("April O'Neill");
		System.out.println(set);
		
		set.add("Leonardo");
		System.out.println(set);
		
		set.remove("Michaelangelo");
		System.out.println(set);
		
		System.out.println(set.contains("Michaelangelo"));
		System.out.println(set.contains("Raphael"));
		System.out.println(set.contains("April O'Neill"));
	}
}
