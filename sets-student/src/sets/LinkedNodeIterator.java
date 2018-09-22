package sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedNodeIterator<E> implements Iterator<E> {
    // TODO (1) define data variables
  private LinkedNode<E> cur;
  
  // Constructors
  public LinkedNodeIterator(LinkedNode<E> head) {
      // TODO (2) choose appropriate parameters and do the initialization
    this.cur = head;
  }

  @Override
  public boolean hasNext() {
    // TODO (3)
    return this.cur != null;
  }

  @Override
  public E next() {
    // TODO (4)
    if (!this.hasNext())
      throw new NoSuchElementException();
    E e = cur.getData();
    cur = cur.getNext();
    return e;
  }

  @Override
  public void remove() {
    // Nothing to change for this method
    throw new UnsupportedOperationException();
  }
}
