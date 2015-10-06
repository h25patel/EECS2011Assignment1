package A1Q3;

import java.util.*;

/**
 * Specializes the stack data structure for comparable elements, and provides a
 * method for determining the minimum element on the stack in O(1) time.
 * 
 * @author jameselder
 */
public class MinStack<E extends Comparable> extends Stack<E> {

	private Stack<E> minStack;
	private Stack<E> stacktemp;

	public MinStack() {
		minStack = new Stack<>();
		stacktemp = new Stack<E>();
	}

	/* must run in O(1) time */
	public E push(E element) {

		boolean MsE = minStack.empty();
		// minStack empty check, if so push element in minStack
		// and stacktemp then return
		if (MsE == true) {
			minStack.push(element);
			stacktemp.push(element);
			return element; // return the pushed element
		} else {
			// if element less then zeor then push element in stacktemp
			if (minStack.peek().compareTo(element) < 0) {
				stacktemp.push(element);
				// compare the element for greater or equal to 0
				// if so push element in stacktemp and minStack then return
			} else if (minStack.peek().compareTo(element) == 0) {
				stacktemp.push(element);
				minStack.push(element);
				return element;
			} else if (minStack.peek().compareTo(element) > 0) {
				stacktemp.push(element);
				minStack.push(element);
				return element;
			}
			// return the element
			return element;
		}
	}

	/* @exception EmptyStackException if this stack is empty. */
	/* must run in O(1) time */
	public synchronized E pop() {
		E sPop = stacktemp.pop(); // pop element in stacktemp
		E mSpop = minStack.peek(); // peek element in minStack
		E sPeek = stacktemp.peek(); // peek element in stacktemp

		// throw empty stack exception
		if (minStack.empty() == true) {
			throw new EmptyStackException();
		}
		// same value in stacktemp and minstack
		else if (sPop.compareTo(mSpop) == 0) {
			minStack.pop();
		}
		// return the stacktemp peek elment
		return sPeek;
	}

	/* Returns the minimum value currenctly on the stack. */
	/* @exception EmptyStackException if this stack is empty. */
	/* must run in O(1) time */
	public synchronized E min() {
		// throw empty stacktemp exception if stack in empty
		if (this.stacktemp.empty()) {
			throw new EmptyStackException();
		} else {
			// or return minStack item
			return minStack.peek();
		}
	}
}