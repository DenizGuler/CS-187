package structures;

import java.util.Iterator;
import java.util.function.Consumer;

public class RecursiveListIterator<E> implements Iterator<E> {

    private LLNode<E> curr;

    public RecursiveListIterator(LLNode<E> head) {
        this.curr = head;
    }

    @Override
    public boolean hasNext() {
        return this.curr != null;
    }

    @Override
    public E next() throws IndexOutOfBoundsException {
        if (!this.hasNext()) throw new IndexOutOfBoundsException();
        E e = curr.data;
        curr = curr.next;
        return e;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
