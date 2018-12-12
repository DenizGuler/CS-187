package structures;

import comparators.IntegerComparator;

import java.util.Comparator;
import java.util.Iterator;

public class MaxQueue<V> implements PriorityQueue<Integer, V> {

    private StudentArrayHeap<Integer, V> heap;

    public MaxQueue() {
        heap = new StudentArrayHeap<>(new IntegerComparator());
    }

    @Override
    public PriorityQueue<Integer, V> enqueue(Integer priority, V value) {
        heap.add(priority, value);
        return this;
    }

    @Override
    public V dequeue() {
        return heap.remove();
    }

    @Override
    public V peek() {
        return heap.peek();
    }

    @Override
    public Iterator<Entry<Integer, V>> iterator() {
        return heap.asList().iterator();
    }

    @Override
    public Comparator<Integer> getComparator() {
        return heap.getComparator();
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
