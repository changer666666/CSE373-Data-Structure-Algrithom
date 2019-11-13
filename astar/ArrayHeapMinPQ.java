package astar;

import heap.ExtrinsicMinPQ;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private PriorityNode[] priorityNode;
    private Map<T, Integer> map;
    private int BORDER = 10;
    private int SIZE = 0;

    public ArrayHeapMinPQ() {
        priorityNode = makeArray(BORDER);
        map = new HashMap<>();
    }

    /*
    Here's a helper method and a method stub that may be useful. Feel free to change or remove
    them, if you wish.
     */

    /**
     * A helper method to create arrays of T, in case you're using an array to represent your heap.
     * You shouldn't need this if you're using an ArrayList instead.
     */
    @SuppressWarnings("unchecked")
    private PriorityNode[] makeArray(int newCapacity) {
        return new ArrayHeapMinPQ.PriorityNode[newCapacity];
    }

    /**
     * A helper method for swapping the items at two indices of the array heap.
     */
    private void swap(int a, int b) {

        map.put(priorityNode[a].item, b);
        map.put(priorityNode[b].item, a);

        PriorityNode temp = priorityNode[a];
        priorityNode[a] = priorityNode[b];
        priorityNode[b] = temp;
    }

    /**
     * Adds an item with the given priority value.
     * Assumes that item is never null.
     * Runs in O(log N) time (except when resizing).
     *
     * @throws IllegalArgumentException if item is already present in the PQ
     */
    @Override
    public void add(T item, double priority) {
        if (item == null) {
            throw new IllegalArgumentException("Item is null!");
        }
        if (this.size() != 0) {
            if (this.contains(item)) {
                throw new IllegalArgumentException("Item is already present in the PQ!");
            }
        }
        if (this.size() >= BORDER - 1) {
            resize(2 * BORDER);
        }
        PriorityNode p = new PriorityNode(item, priority);
        int index = this.size() + 1;
        priorityNode[index] = p;
        map.put(item, index);
        while (index > 1 && (priorityNode[index].compareTo(priorityNode[this.getParent(index)]) < 0)) {
            swap(this.getParent(index), index);
            index = this.getParent(index);
        }
        SIZE++;
    }

    private void resize(int arrLength) {
        PriorityNode[] newArr = makeArray(arrLength + 1);
        for (int i = 1; i <= this.size(); i++) {
            newArr[i] = priorityNode[i];
        }
        priorityNode = newArr;
        BORDER = arrLength;
    }

    /**
     * Returns true if the PQ contains the given item; false otherwise.
     * Runs in O(log N) time.
     */
    @Override
    public boolean contains(T item) {
        return (map.containsKey(item));
    }

    /**
     * Returns the item with the smallest priority.
     * Runs in O(log N) time.
     *
     * @throws NoSuchElementException if the PQ is empty
     */
    @Override
    public T getSmallest() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("PQ is empty!");
        }
        return priorityNode[1].getItem();
    }

    /**
     * Removes and returns the item with the smallest priority.
     * Runs in O(log N) time (except when resizing).
     *
     * @throws NoSuchElementException if the PQ is empty
     */
    @Override
    public T removeSmallest() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("PQ is empty!");
        }
        //remove
        PriorityNode removeNode = priorityNode[1];
        priorityNode[1] = priorityNode[this.size()];
        priorityNode[this.size()] = null;
        SIZE--;
        map.remove(removeNode.item);
        //sink
        int index = 1;
        while (index < this.size()) {
            if (this.size() < this.getLeftChild(index)) {
                break;
            }
            if (priorityNode[this.getLeftChild(index)].compareTo(priorityNode[this.getRightChild(index)]) < 0) {
                if (priorityNode[index].compareTo(priorityNode[this.getLeftChild(index)]) > 0) {
                    swap(index, this.getLeftChild(index));
                    index = this.getLeftChild(index);
                    continue;
                }
                if (priorityNode[index].compareTo(priorityNode[this.getRightChild(index)]) > 0) {
                    swap(index, this.getRightChild(index));
                    index = this.getRightChild(index);
                    continue;
                }
                break;
            } else {
                if (priorityNode[index].compareTo(priorityNode[this.getRightChild(index)]) > 0) {
                    swap(index, this.getRightChild(index));
                    index = this.getRightChild(index);
                    continue;
                }
                if (priorityNode[index].compareTo(priorityNode[this.getLeftChild(index)]) > 0) {
                    swap(index, this.getLeftChild(index));
                    index = this.getLeftChild(index);
                    continue;
                }
                break;
            }
        }
        //resize
        if (this.size() <= BORDER / 4 + 1) {
            resize(BORDER / 4 + 1);
        }
        return removeNode.getItem();
    }

    /**
     * Changes the priority of the given item.
     * Runs in O(log N) time.
     *
     * @throws NoSuchElementException if the item is not present in the PQ
     */
    @Override
    public void changePriority(T item, double priority) {
        if (!this.contains(item)) {
            throw new NoSuchElementException("Item is not present in the PQ!");
        }
        int index = map.get(item);
        priorityNode[index] = new PriorityNode(item, priority);

        //compare to parent
        while (index > 1 && (priorityNode[index].compareTo(priorityNode[this.getParent(index)]) < 0)) {
            swap(this.getParent(index), index);
            index = this.getParent(index);
        }

        //compare to children
        while (index < this.size()) {
            if (this.size() < this.getLeftChild(index)) {
                break;
            }
            if (priorityNode[this.getLeftChild(index)].compareTo(priorityNode[this.getRightChild(index)]) < 0) {
                if (priorityNode[index].compareTo(priorityNode[this.getLeftChild(index)]) > 0) {
                    swap(index, this.getLeftChild(index));
                    index = this.getLeftChild(index);
                    continue;
                }
                if (priorityNode[index].compareTo(priorityNode[this.getRightChild(index)]) > 0) {
                    swap(index, this.getRightChild(index));
                    index = this.getRightChild(index);
                    continue;
                }
                break;
            } else {
                if (priorityNode[index].compareTo(priorityNode[this.getRightChild(index)]) > 0) {
                    swap(index, this.getRightChild(index));
                    index = this.getRightChild(index);
                    continue;
                }
                if (priorityNode[index].compareTo(priorityNode[this.getLeftChild(index)]) > 0) {
                    swap(index, this.getLeftChild(index));
                    index = this.getLeftChild(index);
                    continue;
                }
                break;
            }
        }
    }

    /**
     * Returns the number of items in the PQ.
     * Runs in O(log N) time.
     */
    @Override
    public int size() {
        return SIZE;
    }

    private int getParent(int n) {
        return n / 2;
    }

    private int getLeftChild(int n) {
        return n * 2;
    }

    private int getRightChild(int n) {
        return n * 2 + 1;
    }

    private class PriorityNode implements Comparable<ArrayHeapMinPQ.PriorityNode> {
        private T item;
        private double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(ArrayHeapMinPQ.PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((ArrayHeapMinPQ.PriorityNode) o).getItem().equals(getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }
}
