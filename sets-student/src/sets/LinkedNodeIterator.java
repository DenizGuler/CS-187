package sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedNodeIterator<E> implements Iterator<E> {
    // TODO (1) define data variables
  
  // Constructors
  public LinkedNodeIterator(LinkedNode<E> head) {
      // TODO (2) choose appropriate parameters and do the initialization
  }

  @Override
  public boolean hasNext() {
    // TODO (3)
    return false;
  }

  @Override
  public E next() {
    // TODO (4)
    throw new NoSuchElementException();
  }

  @Override
  public void remove() {
    // Nothing to change for this method
    throw new UnsupportedOperationException();
  }
}
