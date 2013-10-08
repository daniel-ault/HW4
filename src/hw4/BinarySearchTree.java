package hw4;

/**
 * Implementation of a binary search tree.  Now New and Improved (TM) 
 *  with an in-order iterator!
 * 
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

// The extends Comparable<E> means that whatever data type we place in the tree must be
//  from a class that implements the Comparable interface.
public class BinarySearchTree<E extends Comparable<E>> implements Iterable<E>
{
	// Nested class representing a single node in the tree
	private static class BSTNode<E>
	{
		private E data;
		private BSTNode<E> left, right;
		
		private BSTNode(E data, BSTNode<E> left, BSTNode<E> right)
		{
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	// Nested class for an in-order iterator over the tree
	private class InOrderIterator implements Iterator<E>
	{
		private LinkedList<BSTNode<E>> stack = new LinkedList<BSTNode<E>>();
		private BSTNode<E> current = root;
		
		// Returns whether the iterator has more elements
		public boolean hasNext()
		{
			return current != null || !stack.isEmpty();
		}

		// Returns the iterator's next element, and advances the iterator to
		//  the following element
		public E next()
		{
			if (hasNext()) {	// I added this check to ensure that the iterator has a next element before trying to get the next one
    			while (current != null) {	// go left as far as possible
    				stack.push(current);
    				current = current.left;
    			}
    			
    			BSTNode<E> temp = stack.pop();
    			current = temp.right;
    			return temp.data;
			} else
				throw new NoSuchElementException();
		}

		// Removes the last element returned from calling next().  Not supported
		//  in this implementation.
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}

	private BSTNode<E> root;

	// Returns an iterator over the BST (required for the class to implement Iterable)
	public Iterator<E> iterator()
	{
		return new InOrderIterator();
	}
	
	// Pre-order traverses the tree
	public void preOrderTraversal()
	{
		System.out.print("Pre-order traversal: ");
		preOrderTraversal(root);
		System.out.print("\n");
	}
	
	private void preOrderTraversal(BSTNode<E> where)
	{
		if (where != null) {
			System.out.print(where.data + " ");
			preOrderTraversal(where.left);
			preOrderTraversal(where.right);
		}
	}

	// In-order traverses the tree
	public void inOrderTraversal()
	{
		System.out.print("In-order traversal: ");
		inOrderTraversal(root);
		System.out.print("\n");
	}
	
	private void inOrderTraversal(BSTNode<E> where)
	{
		if (where != null) {
			inOrderTraversal(where.left);
			System.out.print(where.data + " ");
			inOrderTraversal(where.right);
		}
	}

	// Deletes the specified someData from the tree.
	public void delete(E someData)
	{
		root = delete(someData, root);
	}

	// Recursively deletes the specified someData from the tree rooted at where.
	//  Returns a reference to where, with the someData deleted.
	private BSTNode<E> delete(E someData, BSTNode<E> where)
	{
		if (where == null) {	// empty tree
			return null;
		} else {
			// compare the someData vs. where.data
			int compare = someData.compareTo(where.data);
			
			if (compare < 0) {			// recursively delete someData from where's left subtree
				where.left = delete(someData, where.left);
				return where;
			} else if (compare > 0) {	// recursively delete someData from where's right subtree
				where.right = delete(someData, where.right);
				return where;
			} else {
				// where contains the data to delete!
				// check to see how many children where has
				
				// case 1: no children - replace where with null
				if (where.left == null && where.right == null)
					return null;
				
				// case 2a: left child only - replace where with its left child
				else if (where.left != null && where.right == null)
					return where.left;
				
				// case 2b: right child only - replace where with its right child
				else if (where.left == null && where.right != null)
					return where.right;
				
				// case 3: the dreaded two children case!
				else {
					// remove the in-order predecessor of where, and replace where.data with that element
					where.data = removeInOrderPred(where);
					
					return where;
				}
			}
		}
	}
	
	// Finds, removes, and returns the in-order predecessor (max element from left subtree)
	//  of the node where.
	// Assumes that 1) where is not null, and 2) where has a left subtree.  Both
	//  assumptions are valid since this method is called from delete only when
	//  where contains the data to remove, and where has two children.
	private E removeInOrderPred(BSTNode<E> where)
	{
		BSTNode<E> parent = where;
		BSTNode<E> temp = where.left;
		
		// when this loop terminates, temp will be at the node containing the in-order predecessor;
		//  parent will be at that node's parent
		while (temp.right != null) {
			parent = temp;
			temp = temp.right;
		}

		if (parent == where)	// we did not move parent or temp at all
			parent.left = temp.left;
		else
			parent.right = temp.left;

		return temp.data;
	}

	
	// Adds the specified newData to the tree (based on the textbook's version)
	public void add(E newData)
	{
		root = add(newData, root);
	}
	
	// Recursively adds the specified newData to the tree rooted at where.
	//  Returns a reference to where, with the newData added.
	private BSTNode<E> add(E newData, BSTNode<E> where)
	{
		if (where == null) {	// create and add the new node
			return new BSTNode<E>(newData, null, null);
		} else {
			// compare the newData vs. where.data
			int compare = newData.compareTo(where.data);

			if (compare < 0)		// recursively add newData to where's left subtree
				where.left = add(newData, where.left);
			else if (compare > 0)	// recursively add newData to where's right subtree
				where.right = add(newData, where.right);

			// (do nothing if compare == 0, i.e., newData already exists in the tree)
			
			return where;
		}
	}
	

	// Alternate version of add (which I personally find a little clearer)
	public void add2(E newData)
	{
		if (root == null)	// special case of adding to the root
			root = new BSTNode<E>(newData, null, null);
		else
			add2(newData, root);
	}
	
	private void add2(E newData, BSTNode<E> where)
	{
		int compare = newData.compareTo(where.data);
		
		if (compare == 0)			// newData already exists in the tree
			return;
		else if (compare < 0 && where.left == null)		// give where a new left child
			where.left = new BSTNode<E>(newData, null, null);
		else if (compare > 0 && where.right == null)	// give where a new right child
			where.right = new BSTNode<E>(newData, null, null);
		else if (compare < 0)
			add2(newData, where.left);					// recursively add newData to where's left subtree
		else
			add2(newData, where.right);					// recursively add newData to where's right subtree
	}

	
	// Searches the tree for the specified someData.  Returns null if the element is not found, or
	//  the element from the tree if it's found.
	public E find(E someData)
	{
		return find(someData, root);
	}

	// Recursively searches the tree rooted at where for the specified someData.  Returns null if
	//  the element is not found, or the element from the tree if it's found.
	private E find(E someData, BSTNode<E> where)
	{
		if (where == null) {	// empty tree - nothing to search!
			return null;
		} else {
			int compare = someData.compareTo(where.data);
			
			if (compare == 0)		// element found!
				return where.data;
			else if (compare < 0)	// recursively search where's left subtree
				return find(someData, where.left);
			else					// recursively search where's right subtree
				return find(someData, where.right);
		}
	}
	
	// Wrapper method for toString
	public String toString()
	{
		return toString(root, "");
	}

	// Recursive implementation of toString.  The indent parameter keeps track of how many spaces to display
	//  before each line.  Each recursive call of toString lengthens this parameter by one space.
	private String toString(BSTNode<E> where, String indent)
	{
		if (where == null)
			return indent + "null";
		else
			return indent + where.data + "\n" + toString(where.left, indent + " ") + "\n" + toString(where.right, indent + " ");
	}
	
	
	public static void main(String[] args)
	{
		Integer[] stuff = {4, 0, 10, -5, 3, 6, 12, 1, 7, 15};
		
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		for (Integer i : stuff)
			bst.add(i);
		
		// access the elements of the BST using our in-order iterator
		Iterator<Integer> it = bst.iterator();
		while (it.hasNext())
			System.out.println(it.next());

		// the for-each syntax is usable on BST objects now, since BinarySearchTree implements
		//  Iterable (i.e., provides a definition for the iterator() method)
		// this loop is a shorthand way of writing the while loop above
		for (Integer i : bst)
			System.out.println(i);
	}
}
