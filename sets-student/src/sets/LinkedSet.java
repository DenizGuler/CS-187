package sets;

import java.util.Iterator;

/**
 * A LinkedList-based implementation of Set
 */

  /********************************************************
   * NOTE: Before you start, check the Set interface in
   * Set.java for detailed description of each method.
   *******************************************************/
  
  /********************************************************
   * NOTE: for this project you must use linked lists
   * implemented by yourself. You are NOT ALLOWED to use
   * Java arrays of any type, or any Collection-based class 
   * such as ArrayList, Vector etc. You will receive a 0
   * if you use any of them.
   *******************************************************/ 

  /********************************************************
   * NOTE: you are allowed to add new methods if necessary,
   * but do NOT add new files (as they will be ignored).
   *******************************************************/

public class LinkedSet<E> implements Set<E> {
  private LinkedNode<E> head = null;

  // Constructors
  public LinkedSet() {
  }

  public LinkedSet(E e) {
    this.head = new LinkedNode<E>(e, null);
  }

  private LinkedSet(LinkedNode<E> head) {
    this.head = head;
  }

  @Override
  public int size() {
    // TODO (1)
    return 0;
  }

  @Override
  public boolean isEmpty() {
    // TODO (2)
    return false;
  }

  @Override
  public Iterator<E> iterator() {
    return new LinkedNodeIterator<E>(this.head);
  }

  @Override
  public boolean contains(Object o) {
    // TODO (3)
    return false;
  }

  @Override
  public boolean isSubset(Set<E> that) {
    // TODO (4)
    return false;
  }

  @Override
  public boolean isSuperset(Set<E> that) {
    // TODO (5)
    return false;
  }

  @Override
  public Set<E> adjoin(E e) {
    // TODO (6)
    return null;
  }

  @Override
  public Set<E> union(Set<E> that) {
    // TODO (7)
    return null;
  }

  @Override
  public Set<E> intersect(Set<E> that) {
    // TODO (8)
    return null;
  }

  @Override
  public Set<E> subtract(Set<E> that) {
    // TODO (9)
    return null;
  }

  @Override
  public Set<E> remove(E e) {
    // TODO (10)
    return null;
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (! (o instanceof Set)) {
      return false;
    }
    Set<E> that = (Set<E>)o;
    return this.isSubset(that) && that.isSubset(this);
  }

  @Override
    public int hashCode() {
    int result = 0;
    for (E e : this) {
      result += e.hashCode();
    }
    return result;
  }
}
