/**
 * SkipList class
 * @author Vikram Thangavel
 * Student number: 216933327
 */

// All imports
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 *  SkipList class
 */
public class SkipList <E> implements List <E>{
	private int size = 0; // Size of list
	private int listHeight = 1; // Height of list
	private Node<E> head; // Head node
	private Random random; // Random variable
	private final double p = 0.5; // probability (coin toss)
	private final int MAX_LEVEL = 20; // Max levels in skip list
	
	/**
	 * Node Class
	 * @param <E> for generics
	 */
	private static class Node <E> {
		E item; // Node data
		Node<E> forward; // Forward link
		Node<E> down; // Downward link
		int distance; // Distance between nodes
		
		// Node constructor
		Node(E element) {
			this.item = element; // Data of node
		}
	}
	/**
	 * Constructs an empty list with a intial random variable, head node (sentinel), two links (forward and downward) and a distance between the nodes.
	 */
	public SkipList() {
		random = new Random();
		head = new Node<E> (null);
		head.distance = 1;
		head.forward = null;
		head.down = null;
	}
	
	/**
	 * Random level generator for a node
	 * @return the number of levels
	 */
	private int randomLevel() {
		int level = 1;
		while (random.nextDouble() < p && level < MAX_LEVEL) {
			level = level + 1;
		}
		return level;
	}

	/**
	 * Indicates the size of the skip list
	 * @return the size of skip list
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Adds node to skip list
	 * @return true for successful insertion
	 */
	@Override
	public boolean add(E e) {
		add(size, e); // Adding the node
		return true; // Return true when successful
	}
	
	/**
	 * Clear method to clear the skip list
	 */
	@Override
	public void clear() {
		size = 0; // Size is reset to 0
		head = null; // Head points to null
		listHeight = 1; // List height is 1
	}

	/**
	 * Getting a node from the skip list by the index value
	 * @return the node's value in that index position
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index > size) { // Exception
			throw new IndexOutOfBoundsException ("Index out of bounds");
		}
		int pos = 0;
		Node<E> x = head; // Head node

		for (x = head; x != null; x = x.down, listHeight--) { // Iteration
			while (x != null && (pos + x.distance) <= index) {
				pos = pos + x.distance;
				x = x.forward;
			}
			return x.item; // Node's data returned
		}
		return null;
	}
	
	/**
	 * Adding a node in a specific index position
	 */
	@Override
	public void add(int index, E element) {
		if (index < 0 || index > size) { //Exception
			throw new IndexOutOfBoundsException ("Index:" + index + ", Size: " + size);
		}
		
		int lvl = randomLevel();
		System.out.println("New item of level: " + lvl);
		if (lvl > listHeight) {
			for (int i = lvl; i > listHeight; i--) {
				Node<E> node = new Node<> (null);
				node.down = head;
				node.forward = null;
				node.distance = size+1;
				head = node;
			}
			listHeight = lvl;
		}
		int pos = 0;
		int currentLevel = listHeight;
		Node <E> lastInserted = null;
		for (Node<E> x = head; x != null; x = x.down, currentLevel--) {
			while (x != null && pos + x.distance <= index) {
				pos = pos + x.distance;
				x = x.forward;
			}
			System.out.println("Position: " + pos + " Current level: " + currentLevel);
			if (currentLevel > lvl) 
				x.distance = x.distance + 1;
			
			else {
				Node<E> y = new Node <> (element);
				System.out.println("Else: Insert at that level");
				Node<E> z = x.forward;
				y.forward = z;
				x.forward = y;
				
				y.distance = pos + x.distance - index;
				x.distance = index + 1 - pos;
				if (lastInserted != null) lastInserted.down = y;
				lastInserted = y;
			}
		}
		size++;
	}
	
	/**
	 * Remove a node from the skip list by its index
	 * @return node removed
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException ("Index out of bounds");
		}
		
		int pos = 0;
		Node<E> x = head;
		Node <E> update = null;

		for (x = head; x != null; x = x.down, listHeight--) {
			while (x != null && pos + x.distance <= index) {
				pos = pos + x.distance;
				x = x.forward;
			}
			update = x;
		}
		x = update;
		x = x.forward;
		for (int i = 1; i < listHeight; i++) {
			if (update.forward == x) {
				update.forward = x.forward;
				update.distance = update.distance + x.distance - 1;
			}
			else {
				update.distance = update.distance - 1;
			}
			size--;
		}
		
		while(head.forward == null && listHeight > 1) {
			listHeight = listHeight-1;
		}
		return null;
	}
	
	/** 
	 * Creating the skip list
	 * @return skip list
	 */
	@Override
    public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("SkipList of height: " + listHeight + " Size: " + size + "\n");
		for (Node<E> node = head; node != null; node = node.down) {
			for(Node<E> node2 = node; node2 != null; node2 = node2.forward)
				sb.append("(" + node2.item + ")-" + node2.distance + "- ");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	// Unsupported methods
	
	@Override
	public boolean isEmpty() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<E> iterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}
}