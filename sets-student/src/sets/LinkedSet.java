package sets;

import sun.awt.image.ImageWatched;

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
    //LinkedNode<E> node = head;
    int counter = 0;
    for (E e : this)
      counter++;
    return counter;
  }

  @Override
  public boolean isEmpty() {
    // TODO (2)
    return head == null;
  }

  @Override
  public Iterator<E> iterator() {
    return new LinkedNodeIterator<E>(this.head);
  }

  @Override
  public boolean contains(Object o) {
    // TODO (3)
    for (E e : this){
      if (e.equals(o))
        return true;
    }
    return false;
  }

  @Override
  public boolean isSubset(Set<E> that) {
    // TODO (4)
    for (E e : this)
      if (!that.contains(e))
        return false;
    return true;
    //return false;
  }

  @Override
  public boolean isSuperset(Set<E> that) {
    // TODO (5)
    return that.isSubset(this);
  }

  @Override
  public Set<E> adjoin(E e) {
    // TODO (6)

    if (this.head == null)
      return new LinkedSet<E>(e);

    if (this.contains(e))
      return new LinkedSet<E>(head);

    return new LinkedSet<E>(new LinkedNode<E>(e, head));
  }

  @Override
  public Set<E> union(Set<E> that) {
    // TODO (7)
    LinkedSet<E> newSet = new LinkedSet<E>(head);
    for (E e : that)
      newSet = (LinkedSet<E>) newSet.adjoin(e);
    return newSet;
  }

  @Override
  public Set<E> intersect(Set<E> that) {
    // TODO (8)
    LinkedSet<E> newSet = new LinkedSet<E>();
    for (E e : that)
      if (this.contains(e))
        newSet = (LinkedSet<E>) newSet.adjoin(e);
    return newSet;
  }

  @Override
  public Set<E> subtract(Set<E> that) {
    // TODO (9)
    LinkedSet<E> newSet = new LinkedSet<E>();
    for (E e : this)
      if (!that.contains(e))
        newSet = (LinkedSet<E>) newSet.adjoin(e);
    return newSet;
  }

  @Override
  public Set<E> remove(E e) {
    // TODO (10)
    return this.subtract(new LinkedSet<>(e));
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
