package structures;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class BinarySearchTree<T extends Comparable<T>> implements
        BSTInterface<T> {
    protected BSTNode<T> root;

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return subtreeSize(root);
    }

    protected int subtreeSize(BSTNode<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + subtreeSize(node.getLeft())
                    + subtreeSize(node.getRight());
        }
    }

    public boolean contains(T t) {
        return get(t) != null;
    }

    public boolean remove(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        boolean result = contains(t);
        if (result) {
            root = removeFromSubtree(root, t);
        }
        return result;
    }

    private BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
        // node must not be null
        int result = t.compareTo(node.getData());
        if (result < 0) {
            node.setLeft(removeFromSubtree(node.getLeft(), t));
            if (node.getLeft() != null) {
                node.getLeft().setParent(node);
            }
            return node;
        } else if (result > 0) {
            node.setRight(removeFromSubtree(node.getRight(), t));
            if (node.getRight() != null) {
                node.getRight().setParent(node);
            }
            return node;
        } else { // result == 0
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else { // neither child is null
                T predecessorValue = getHighestValue(node.getLeft());
                node.setLeft(removeRightmost(node.getLeft()));
                if (node.getLeft() != null) {
                    node.getLeft().setParent(node);
                }
                node.setData(predecessorValue);
                return node;
            }
        }
    }

    private T getHighestValue(BSTNode<T> node) {
        // node must not be null
        if (node.getRight() == null) {
            return node.getData();
        } else {
            return getHighestValue(node.getRight());
        }
    }

    private BSTNode<T> removeRightmost(BSTNode<T> node) {
        // node must not be null
        if (node.getRight() == null) {
            return node.getLeft();
        } else {
            node.setRight(removeRightmost(node.getRight()));
            if (node.getRight() != null) {
                node.getRight().setParent(node);
            }
            return node;
        }
    }

    public T get(T t) throws NullPointerException {
        if (t == null) throw new NullPointerException();
        return get(t, root);
    }

    private T get(T t, BSTNode<T> node) {
        if (node == null) return null;
        if (node.getData().equals(t)) return node.getData();
        if (t.compareTo(node.getData()) > 0) return get(t, node.getRight());
        return get(t, node.getLeft());
    }

    public void add(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        root = addToSubtree(root, new BSTNode<T>(t, null, null));
    }

    protected BSTNode<T> addToSubtree(BSTNode<T> node, BSTNode<T> toAdd) {
        if (node == null) {
            return toAdd;
        }
        int result = toAdd.getData().compareTo(node.getData());
        if (result <= 0) {
            node.setLeft(addToSubtree(node.getLeft(), toAdd));
            if (node.getLeft() != null) {
                node.getLeft().setParent(node);
            }
        } else {
            node.setRight(addToSubtree(node.getRight(), toAdd));
            if (node.getRight() != null) {
                node.getRight().setParent(node);
            }
        }
        return node;
    }

    @Override
    public T getMinimum() {
        if (root == null) return null;
        BSTNode<T> node = root;
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node.getData();
    }

    @Override
    public T getMaximum() {
        if (root == null) return null;
        BSTNode<T> node = root;
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node.getData();
    }

    @Override
    public int height() {
        return height(root) - 1;
    }

    private int height(BSTNode<T> node) {
        if (node == null) return 0;
        int right = height(node.getRight());
        int left = height(node.getLeft());
        if (right > left)
            return right + 1;
        return left + 1;
    }

    public Iterator<T> preorderIterator() {
        Queue<T> queue = new LinkedList<T>();
        preorderTraverse(queue, root);
        return queue.iterator();
    }

    private void preorderTraverse(Queue<T> queue, BSTNode<T> node) {
        if (node != null) {
            queue.add(node.getData());
            preorderTraverse(queue, node.getLeft());
            preorderTraverse(queue, node.getRight());
        }
    }

    public Iterator<T> inorderIterator() {
        Queue<T> queue = new LinkedList<T>();
        inorderTraverse(queue, root);
        return queue.iterator();
    }

    private void inorderTraverse(Collection<T> col, BSTNode<T> node) {
        if (node != null) {
            inorderTraverse(col, node.getLeft());
            col.add(node.getData());
            inorderTraverse(col, node.getRight());
        }
    }

    public Iterator<T> postorderIterator() {
        Queue<T> queue = new LinkedList<T>();
        postorderTraverse(queue, root);
        return queue.iterator();
    }

    private void postorderTraverse(Queue<T> queue, BSTNode<T> node) {
        if (node != null) {
            postorderTraverse(queue, node.getLeft());
            postorderTraverse(queue, node.getRight());
            queue.add(node.getData());
        }
    }

    @Override
    public boolean equals(BSTInterface<T> other) throws NullPointerException {
        if (other == null) throw new NullPointerException();
        return equals(root, other.getRoot());
    }

    private boolean equals(BSTNode<T> node, BSTNode<T> otherNode) {
        if (node == null && otherNode == null) return true;
        if (node == null || otherNode == null) return false;
        return node.getData().equals(otherNode.getData()) &&
                equals(node.getLeft(), otherNode.getLeft()) &&
                equals(node.getRight(), otherNode.getRight());

    }


    @Override
    public boolean sameValues(BSTInterface<T> other) throws NullPointerException {
        if (other == null) throw new NullPointerException();
        Iterator<T> thisIter = inorderIterator();
        Iterator<T> otherIter = other.inorderIterator();
        while (thisIter.hasNext() && otherIter.hasNext())
            if (!thisIter.next().equals(otherIter.next()))
                return false;
        return !thisIter.hasNext() && !otherIter.hasNext();
    }

    @Override
    public boolean isBalanced() {
        return Math.pow(2, height()) <= size() && size() < Math.pow(2, height() + 1);
    }


    @Override
    @SuppressWarnings("unchecked")

    public void balance() {
        ArrayList<T> orderedList = new ArrayList<T>();
        inorderTraverse(orderedList, root);
        int listSize = orderedList.size();
        this.root = null;
        balance(orderedList);
    }

    private void balance(ArrayList<T> list) {
        if (list.isEmpty()) return;
        if (list.size() == 1) {
            add(list.get(0));
            return;
        }
        add(list.get(list.size() / 2));
        balance(new ArrayList<>(list.subList(0, list.size() / 2)));
        balance(new ArrayList<>(list.subList(list.size() / 2 + 1, list.size())));
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY
        return root;
    }

    public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
        // header
        int count = 0;
        String dot = "digraph G { \n";
        dot += "graph [ordering=\"out\"]; \n";
        // iterative traversal
        Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
        queue.add(root);
        BSTNode<T> cursor;
        while (!queue.isEmpty()) {
            cursor = queue.remove();
            if (cursor.getLeft() != null) {
                // add edge from cursor to left child
                dot += cursor.getData().toString() + " -> "
                        + cursor.getLeft().getData().toString() + ";\n";
                queue.add(cursor.getLeft());
            } else {
                // add dummy node
                dot += "node" + count + " [shape=point];\n";
                dot += cursor.getData().toString() + " -> " + "node" + count
                        + ";\n";
                count++;
            }
            if (cursor.getRight() != null) {
                // add edge from cursor to right child
                dot += cursor.getData().toString() + " -> "
                        + cursor.getRight().getData().toString() + ";\n";
                queue.add(cursor.getRight());
            } else {
                // add dummy node
                dot += "node" + count + " [shape=point];\n";
                dot += cursor.getData().toString() + " -> " + "node" + count
                        + ";\n";
                count++;
            }

        }
        dot += "};";
        return dot;
    }

    public static void main(String[] args) {
        for (String r : new String[]{"a", "b", "c", "d", "e", "f", "g"}) {
            BSTInterface<String> tree = new BinarySearchTree<String>();
            for (String s : new String[]{"d", "b", "a", "c", "f", "e", "g"}) {
                tree.add(s);
            }
            Iterator<String> iterator = tree.inorderIterator();
            while (iterator.hasNext()) {
                System.out.print(iterator.next());
            }
            System.out.println();
            iterator = tree.preorderIterator();
            while (iterator.hasNext()) {
                System.out.print(iterator.next());
            }
            System.out.println();
            iterator = tree.postorderIterator();
            while (iterator.hasNext()) {
                System.out.print(iterator.next());
            }
            System.out.println();

            System.out.println(tree.remove(r));

            iterator = tree.inorderIterator();
            while (iterator.hasNext()) {
                System.out.print(iterator.next());
            }
            System.out.println();
        }

        BSTInterface<String> tree = new BinarySearchTree<String>();
        for (String r : new String[]{"a", "b", "c", "d", "e", "f", "g"}) {
            tree.add(r);
        }
        System.out.println(tree.size());
        System.out.println(tree.height());
        System.out.println(tree.isBalanced());
        tree.balance();
        System.out.println(tree.size());
        System.out.println(tree.height());
        System.out.println(tree.isBalanced());
    }
}