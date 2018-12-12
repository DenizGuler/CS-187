package structures;

import org.omg.CORBA.INITIALIZE;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class StudentArrayHeap<P, V> extends AbstractArrayHeap<P, V> {


    public StudentArrayHeap(Comparator<P> comparator) {
        super(comparator);
    }

    @Override
    protected int getLeftChildOf(int index) throws IndexOutOfBoundsException {
        if (index < 0) throw new IndexOutOfBoundsException();
        return index*2+1;
    }

    @Override
    protected int getRightChildOf(int index) throws IndexOutOfBoundsException {
        if (index < 0) throw new IndexOutOfBoundsException();
        return index*2+2;
    }

    @Override
    protected int getParentOf(int index) throws IndexOutOfBoundsException {
        if (index < 1) throw new IndexOutOfBoundsException();
        return (index-1)/2;
    }

    @Override
    protected void bubbleUp(int index) {
        if (index < 1 || comparator.compare(heap.get(getParentOf(index)).getPriority(), heap.get(index).getPriority()) >= 0) return;
        Entry<P, V> temp = heap.get(index);
        heap.set(index, heap.get(getParentOf(index)));
        heap.set(getParentOf(index), temp);
        bubbleUp(getParentOf(index));

    }

    @Override
    protected void bubbleDown(int index) {
        if (index == size() - 1) return;
        Entry<P, V> temp = heap.get(index);
        int largerIndex = getRightChildOf(index) > size() - 1
                || comparator.compare(heap.get(getLeftChildOf(index)).getPriority(), heap.get(getRightChildOf(index)).getPriority()) >= 0
                ?getLeftChildOf(index):getRightChildOf(index);
        if (largerIndex < size() && comparator.compare(heap.get(index).getPriority(), heap.get(largerIndex).getPriority()) < 0) {
            heap.set(index, heap.get(largerIndex));
            heap.set(largerIndex, temp);
            bubbleDown(largerIndex);
        }
    }
}


