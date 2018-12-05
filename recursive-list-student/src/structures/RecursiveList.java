package structures;


import javax.print.attribute.standard.NumberUp;
import java.util.Iterator;

public class RecursiveList<E> implements ListInterface<E>{

    private int size = 0;
    private LLNode<E> head;
    private LLNode<E> tail;
    private boolean notFound;

    @Override
    public int size() {
        return size;
    }

    @Override
    public ListInterface<E> insertFirst(E elem) throws NullPointerException {
        if (elem == null) throw new NullPointerException();
        LLNode<E> node = new LLNode<>(elem, null, head);
        if (head == null) {
            tail = node;
            head = node;
        } else {
            head.prev = node;
            head = node;
        }
        size++;
        return this;
    }

    @Override
    public ListInterface<E> insertLast(E elem) throws NullPointerException {
        if (elem == null) throw new NullPointerException();
        LLNode<E> node = new LLNode<>(elem, tail, null);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
        return this;
    }

    @Override
    public ListInterface<E> insertAt(int index, E elem) throws NullPointerException, IndexOutOfBoundsException {
        if (elem == null) throw new NullPointerException();
        if (index == size)
            return insertLast(elem);
        LLNode<E> indexNode = nodeAt(index, head);
        LLNode<E> node = new LLNode<E>(elem, indexNode.prev, indexNode);
        if (indexNode.prev != null)
            indexNode.prev.next = node;
        else
            head = node;
        indexNode.prev = node;
        size++;
        return this;
    }


    @Override
    public E removeFirst() throws IllegalStateException {
        E e = getFirst();
        head = head.next;
        if (head != null)
            head.prev = null;
        else
            tail = null;
        size--;
        return e;
    }

    @Override
    public E removeLast() throws IllegalStateException {
        E e = getLast();
        tail = tail.prev;
        if (tail != null)
            tail.next = null;
        else
            head = null;
        size--;
        return e;
    }

    @Override
    public E removeAt(int i) throws IndexOutOfBoundsException {
        LLNode<E> indexNode = nodeAt(i, head);
        E e = indexNode.data;

        if (indexNode.prev != null)
            indexNode.prev.next = indexNode.next;
        else
            head = indexNode.next;
        if (indexNode.next != null)
            indexNode.next.prev = indexNode.prev;
        else
            tail = indexNode.prev;
        size--;
        return e;
    }

    @Override
    public E getFirst() throws IllegalStateException {
        if (head == null) throw new IllegalStateException();
        return head.data;
    }

    @Override
    public E getLast() throws IllegalStateException {
        if (tail == null) throw new IllegalStateException();
        return tail.data;
    }

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        LLNode<E> indexNode = nodeAt(i, head);
        return indexNode.data;
    }

    @Override
    public boolean remove(E elem) throws NullPointerException {
        if (elem == null) throw new NullPointerException();
        return remove(elem, head);
    }

    private boolean remove(E elem, LLNode<E> curr) {
        if (curr == null) return false;
        if (curr.data.equals(elem)) {
            if (curr.prev != null)
                curr.prev.next = curr.next;
            else
                head = curr.next;
            if (curr.next != null)
                curr.next.prev = curr.prev;
            else
                tail = curr.prev;
            size--;
            return true;
        }
        System.arr
        return remove(elem, curr.next);
    }

    @Override
    public int indexOf(E elem) throws NullPointerException {
        if (elem == null) throw new NullPointerException();
        int result = indexOf(elem, head) - 1;
        if (notFound) {
            result = -1;
            notFound = false;
        }
        return result;
    }

    private int indexOf(E elem, LLNode<E> curr) {
        if (curr == null){
            notFound = true;
            return 0;
        }
        if (curr.data == elem) return 1;
        return 1 + indexOf(elem, curr.next);
    }

    @Override
    public boolean isEmpty() {
        return head == null && tail == null;
    }

    @Override
    public Iterator<E> iterator() {
        return new RecursiveListIterator<E>(head);
    }

    public String toString() {
        return toString(iterator());
    }

    private String toString(Iterator <E> iter) {
        if (!iter.hasNext()) return "";
        return iter.next().toString() + ", " + toString(iter);
    }


    private LLNode<E> nodeAt(int index, LLNode<E> curr) throws IndexOutOfBoundsException {
        if (index >= size() || index < 0) throw new IndexOutOfBoundsException();
        if (index == 0)
            return curr;
        return nodeAt(index - 1, curr.next);
    }

}

class LLNode<E> {
    public LLNode<E> prev;
    public LLNode<E> next;
    public E data;
    public LLNode(E data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
    public LLNode(E data, LLNode<E> prev, LLNode<E> next){
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    public String toString() {
        return data.toString();
    }
}
