package structures;

import java.util.NoSuchElementException;

/**************************************************************************************
 * NOTE: before starting to code, check support/structures/UnboundedQueueInterface.java
 * for detailed explanation of each interface method, including the parameters, return
 * values, assumptions, and requirements
 ***************************************************************************************/
public class Queue<T> implements UnboundedQueueInterface<T> {

	Node<T> tail;
	int size;
	public Queue() {		
		// TODO 1
		tail = null;
		size = 0;

    }
	
	public Queue(Queue<T> other) {
		// TODO 2
		this();
		Queue<T> temp = new Queue<>();
		while (!other.isEmpty()) {
			this.enqueue(other.peek());
			temp.enqueue(other.dequeue());
		}
		while (!temp.isEmpty()){
			other.enqueue(temp.dequeue());
		}
	}
	
	@Override
	public boolean isEmpty() {
		// TODO 3
		return tail == null;
	}

	@Override
	public int size() {
		// TODO 4
		return size;
	}

	@Override
	public void enqueue(T element) {
		// TODO 5
		Node<T> node = new Node<>(element, null);
		if (isEmpty()) {
			node.next = node;
			tail = node;
		} else {
			node.next = tail.next;
			tail.next = node;
			tail = node;
		}
		size++;
	}

	@Override
	public T dequeue() throws NoSuchElementException {
            // TODO 6
		if (isEmpty()) throw new NoSuchElementException();
		T t;
		if (tail.next == tail){
			t = peek();
			tail = null;
			size--;
			return t;
		}
		t = peek();
		tail.next = tail.next.next;
		size--;
		return t;
	}

	@Override
	public T peek() throws NoSuchElementException {
		// TODO 7
		if (isEmpty()) throw new NoSuchElementException();
		if (tail.next == null){
			return tail.data;
		}
		return tail.next.data;
	}

	
	@Override
	public UnboundedQueueInterface<T> reversed() {
		// TODO 8
		Queue<T> rev = new Queue<>();
		Queue<T> copy = new Queue<>(this);
		rev.recursiveReversed(copy);
		return rev;
	}

	private void recursiveReversed(Queue<T> copy){
		T temp;
		if (!copy.isEmpty()){
			temp = copy.dequeue();
			recursiveReversed(copy);
			this.enqueue(temp);
		}
	}
}

class Node<T> {
	public T data;
	public Node<T> next;
	public Node(T data) { this.data=data;}
	public Node(T data, Node<T> next) {
		this.data = data; this.next=next;
	}
}

