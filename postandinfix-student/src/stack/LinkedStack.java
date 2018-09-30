package stack;

/**
 * A {@link LinkedStack} is a generic stack that is implemented using
 * a Linked List structure to allow for unbounded size.
 */
public class LinkedStack<T> {
	
	// TODO: define class variables here
	private LLNode<T> head;
	private int size;

	/**
	 * Remove and return the top element on this stack.
	 * If stack is empty, return null (instead of throw exception)
	 */
	public T pop() {
		// TODO
		if (this.isEmpty())
			return null;
		size--;
		T elem = head.info;
		head = head.link;
		return elem;
	}

	/**
	 * Return the top element of this stack (do not remove the top element).
	 * If stack is empty, return null (instead of throw exception)
	 */
	public T top() {
		// TODO
		if (this.isEmpty())
			return null;
		return head.info;
	}

	/**
	 * Return true if the stack is empty and false otherwise.
	 */
	public boolean isEmpty() {
		// TODO
		return size() == 0;
	}

	/**
	 * Return the number of elements in this stack.
	 */
	public int size() {
		// TODO
		return size;
	}

	/**
	 * Pushes a new element to the top of this stack.
	 */
	public void push(T elem) {
		// TODO
		LLNode<T> newNode = new LLNode<>(elem);
		newNode.link = head;
		head = newNode;
		size++;
	}

}
