import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * 
 * @author Vikram
 * 
 * Array representation of the binary heap
 * Priority queue that extends the AbstractQueue and Comparable
 *
 * @param <E>
 */

public class PriorityQueue2011<E extends Comparable <? super E>> extends AbstractQueue<E> {
	private static final int INITIAL_CAPACITY = 11; // Initial capacity
	private E[] pq; // Priority queue
	private int size; // Size of queue
	private boolean heap; // Boolean variable for the priority queue
	
	/**
	 * Priority queue constructor that creates a comparable array with a initial capacity of 11 and a size of 0
	 */
	public PriorityQueue2011() {
		this.pq = (E[]) new Comparable[INITIAL_CAPACITY];
		size = 0;
		heap = true;
	}
	
	// Protected methods (Utility methods)
	
	/**
	 * Method that swaps the following indexes as parameters in the array
	 * @param i
	 * @param j
	 */
	protected void swap(int i, int j) {
		E temp = pq[i];
		pq[i] = pq[j];
		pq[j]= temp;
	}
	
	/**
	 * Method to check for the right child element
	 * @param index
	 * @return boolean value
	 */
	protected boolean rightChild(int i) {
		return (i * 2 + 1) <= size;
	}
	
	/**
	 * Method to check for the left child element
	 * @param index
	 * @return boolean value
	 */
	protected boolean leftChild(int i) {
		return (i * 2) <= size;
	}
	
	/**
	 * Method to check for the parent element
	 * @param index
	 * @return boolean value
	 */
	protected boolean parent(int i) {
		return i > 1;
	}
	
	/**
	 * Method to make the element go down in the binary heap
	 */
	protected void sink() {
		int index = 1;
		if(heap) {
			while(leftChild(index)) {
				int small = index*2;
				if(rightChild(index) && pq[index * 2].compareTo(pq[index * 2 + 1]) > 0) {
					small = index * 2 + 1;
				}
				
				if(pq[index].compareTo(pq[small]) > 0) {
					swap(index, small);
				}
				else break;
				index = small;
			}
		}
		else {
			while(leftChild(index)) {
				int large = index*2;
				if(rightChild(index) && pq[index*2].compareTo(pq[index*2+1]) < 0) {
					large = index*2+1;
				}
				if(pq[index].compareTo(pq[large]) < 0) {
					swap(index, large);
				}
				else break;
				index = large;
			}
		}
	}
	
	/**
	 * Method to make the element go up in the binary heap
	 */
	protected void swim() {
		int index = size;
		if(heap) {
			while(parent(index) && pq[index/2].compareTo(pq[index]) > 0) {
				swap(index, index/2);
				index = index/2;
			}
		}
	}
	
	/**
	 * Increase the size of array
	 * @return new enlarged array
	 */
	protected E[] grow() {
		return Arrays.copyOf(pq, pq.length + INITIAL_CAPACITY);
	}
	
	// Public Methods
	
	/**
	 * Offer method to add the element to the queue
	 */
	@Override
	public boolean offer(E e) {
		if(this.size >= pq.length - 1) {
			pq = this.grow();
		}
		size++;
		pq[size] = e;
		swim();
		return true;	
	}
	
	/**
	 * Add method to add the element to the queue
	 */
	@Override
	public boolean add(E e) {
		offer(e);
		return true;
	}

	/**
	 * Poll method to remove the head element of the queue
	 */
	@Override
	public E poll() {
		if (size == 0) {
			return null;
		}

		E removed = peek();
		swap(1, size);
		pq[size] = null;
		size--;
		sink();
		return removed;
	}
	
	/**
	 * Remove method to remove the head element of the queue
	 */
	@Override  
	public E remove() {
		if(size == 0) {
			throw new NoSuchElementException("Queue is empty");
		}
		return poll();
	}

	/**
	 * Peek method to return the head element of the queue
	 */
	@Override
	public E peek() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return pq[1];
	}
	
	/**
	 * Element method to return the head element of the queue
	 */
	@Override
	public E element() {
		if (size == 0) 
			return null;
		else
			return peek();
	}

	/**
	 * Returns size of the queue
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * ToString method
	 */
	@Override
	public String toString() {
		String s = "[";
		for (int i = 0; i < pq.length; ++i) {
			if(pq[i] != null) { 
				s += pq[i] + "";
				
				if(i < size) {
					s += ", ";
				}	
			}
		}
		s += "]";
		return s;
	}
	
	/**
	 * ToTree method
	 * @return array as a binary tree
	 * 
	 */
	public String toTree(){
		String s = "";
        int j = 0;
        for (int i = 1; i <= size; i++) {
        	for(int a = 0; a < size - i; a++) {
        		s += "  ";
        	}
            final double rowLength = Math.pow(2, j); 
            for (int k = 0; k < rowLength && i <= size; k++){
                s += String.format("%4s", pq[i++]) + " ";
            }
            i--;
            j++;
            s += "\n";
        }
        return s;
    }
    

	/**
	 * Unsupported method
	 */
	@Override
	public Iterator<E> iterator() {
		throw new UnsupportedOperationException();
	}
}