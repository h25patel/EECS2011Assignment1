package A1Q1;

import java.util.*;

/**
 * Represents a sparse numeric vector. Elements are comprised of a (long)
 * location index an a (double) value. The vector is maintained in increasing
 * order of location index, which facilitates numeric operations like inner
 * products (projections). Note that location indices can be any integer from 1
 * to Long.MAX_VALUE. The representation is based upon a singly-linked list. The
 * following methods are supported: iterator, getSize, getFirst, add, remove,
 * and dot, which takes the dot product of the with a second vector passed as a
 * parameter.
 * 
 * @author jameselder
 */
public class SparseNumericVector implements Iterable {

	protected SparseNumericNode head = null;
	protected SparseNumericNode tail = null;
	protected long size;

	/**
	 * Iterator
	 */
	@Override
	public Iterator<SparseNumericElement> iterator() { // iterator
		return new SparseNumericIterator(this);
	}

	/**
	 * @return number of non-zero elements in vector
	 */
	public long getSize() {
		return size;
	}

	/**
	 * @return the first node in the list.
	 */
	public SparseNumericNode getFirst() {
		return head;
	}

	/**
	 * Add the element to the vector. It is inserted to maintain the vector in
	 * increasing order of index. If an element with the same index already
	 * exists, its value is updated. If an element with 0 value is passed, it is
	 * ignored.
	 * 
	 * @param e
	 *            element to add
	 */
	public void add(SparseNumericElement e) {
		// implement this method
		
		//create an element and node to iterate through the current list
		SparseNumericElement nodeElem = new SparseNumericElement(e.getIndex(), e.getValue());
	       SparseNumericNode newNode = new SparseNumericNode(nodeElem, null);
	    // check if you're at the end of the list. i.e. first the node into the list, by setting the previous node to
		// point to the new node and the new node to point to the next node.
	       if(this.head == null){
	    	   this.head = newNode;
	    	   this.tail = head;
	    	   size++;
	       }else if( this.head == this.tail && nodeElem.getIndex() < head.getElement().getIndex()){
	    	   newNode.setNext(this.head);
	    	   this.head = newNode;
	    	   size++;
	       }else if(this.head == this.tail && nodeElem.getIndex() > this.head.getElement().getIndex()){
	    	   this.head.setNext(newNode);
	    	   this.tail = this.head.getNext();
	    	   size++;	   
	       }else{
	    	   SparseNumericNode first = head;
	    	   SparseNumericNode second = first.getNext();

	    	// if you're somewhere else in the list, look for where to add the
				// element.
	    	   while(first.getNext() != null){
	    		   
	    			   if(first.getElement().getIndex() == nodeElem.getIndex()){
	    				   newNode.setNext(first.getNext());
	    			   }
	    			   if(nodeElem.getIndex() < second.getElement().getIndex()){
	    				   newNode.setNext(second);
	    				   first.setNext(newNode);
	    				   size++;
	    				   break;
	    			   } //get the next elements
	    			   first = first.getNext();
	    			   second = second.getNext();
	    		   
	    	   }
	       } 
	}

	/**
	 * If an element with the specified index exists, it is removed and the
	 * method returns true. If not, it returns false.
	 *
	 * @param index
	 *            of element to remove
	 * @return true if removed, false if does not exist
	 */
	public boolean remove(Long index) {
		// implement this method
		// this return statement is here to satisfy the compiler - replace it
		// with your code.

		// initiate a check value to be false.
		boolean check = false;

		// Create a node starting at the head.
		SparseNumericNode prevNode = this.head;

		// Start looping through until you reach the index.
		while (prevNode.getNext().getElement().getIndex() != index) {

			// If you reach the trailer without removing, removed false.
			if (prevNode.equals(tail)) {
				return false;
			}

			// Move to the next node.
			prevNode = prevNode.getNext();
		}

		// If you're at the node before the one of the desired index, set the
		// current node equal to the next, next node and set the desired node to
		// point to null.
		if (prevNode.getNext().getElement().getIndex() == index) {
			SparseNumericNode nextNode = prevNode.getNext();
			prevNode.setNext(nextNode.getNext());
			nextNode.setNext(null);
			check = true;
		}

		return check;

	}

	/**
	 * Returns the inner product of the vector with a second vector passed as a
	 * parameter. The vectors are assumed to reside in the same space. Runs in
	 * O(m+n) time, where m and n are the number of non-zero elements in each
	 * vector.
	 * 
	 * @param Y
	 *            Second vector with which to take inner product
	 * @return result of inner product
	 */

	public double dot(SparseNumericVector Y) {
		// implement this method
		// this return statement is here to satisfy the compiler - replace it
		// with your code.
	double dotProd = 0;

		// Initialize iterators over the lists.
		SparseNumericIterator first = new SparseNumericIterator(this);
		SparseNumericIterator second = new SparseNumericIterator(Y);

		while (first.hasNext() && second.hasNext()) {

			// If their indexes are the same, multiply and add them to
			// the sum
			if (first.position.getElement().getIndex() == second.position.getElement().getIndex())
				dotProd += first.next().getValue() * second.next().getValue();

			// If the second vector index is higher than the first, get the next
			// first and keep the same second element.
			else if ((first.position.getElement().getIndex()) < (second.position.getElement().getIndex())) {
				first.next();
			}

			// only other option is if the second is smaller.
			else
				second.next();

		}

		return dotProd;
		
	}

	/**
	 * returns string representation of sparse vector
	 */

	@Override
	public String toString() {
		String sparseVectorString = "";
		Iterator<SparseNumericElement> it = iterator();
		SparseNumericElement x;
		while (it.hasNext()) {
			x = it.next();
			sparseVectorString += "(index " + x.getIndex() + ", value " + x.getValue() + ")\n";
		}
		return sparseVectorString;
	}
}
