import java.util.NoSuchElementException;
import java.util.Arrays;

public class MyDeque<E> {
    private E[] data;
    private int size, start, end;

    private final static int DEFAULT_CAPACITY = 10;

    public MyDeque() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public MyDeque(int initialCapacity) {
        this.data = (E[]) new Object[initialCapacity];
        this.start = 0;
        this.end = 0;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("{");
        for (int i = 0, c = start; i < size; i++, c++) {
            if (c == data.length) c = 0;
            str.append(data[c]);
            str.append(" ");
        }
//        int i = start;
//        do {
//            str.append(data[i++]);
//            str.append(" ");
//            if (i == data.length) {
//                i = 0;
//            }
//        } while (i != end);
//        str.append(data[i]);
//        str.append(" ");
        
        str.append("}");
        return str.toString();
    }

    /**
     * Add: en-queue
     */
    public void addFirst(E element) {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }

        if (size == data.length) {
            grow();
        }

        if (data[start] != null)
            start--;

        if (start < 0) {
            start = data.length - 1;
        }

        data[start] = element;
        size++;
    }

    /**
     * Add: push
     */
    public void addLast(E element) {

        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }

        if (size == data.length) {
            grow();
        }

        if (data[end] != null)
            end++;
        if (end >= data.length) {
            end = 0;
        }

        data[end] = element;
        size++;
    }

    /**
     * 
     * @param start Starting point of the data array, inclusive
     */
    private void grow() {
        int oldCapacity = data.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        @SuppressWarnings("unchecked")
        E[] newData = (E[]) new Object[newCapacity];

        
        for (int i = 0, c = start; i < size; i++, c++) {
            if (c == data.length) c = 0;
            newData[i] = data[c];
        }
        
//        int oldIndex = start;
//        int newIndex = 0;
//        do {
//            newData[newIndex++] = data[oldIndex++];
//            if (oldIndex == data.length) {
//                oldIndex = 0;
//            }
//        } while (oldIndex != end);
//        newData[newIndex] = data[oldIndex];

        data = newData;
        start = 0;
        end = size - 1;
    }

    public E removeFirst() {

        if (size == 0) {
            throw new NoSuchElementException("No more elements in collection");
        }

        E e = data[start];

        // We don't have to delete old value, since it is no longer pointed to
        start++;
        size--;

        // If start index is out of array, reset it
        if (start >= data.length) {
            start = 0;
        }

        return e;
    }

    public E removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("No more elements in collection");
        }

        E e = data[end];

        // We don't have to delete old value, since it is no longer pointed to
        end--;
        size--;

        // If start index is out of array, reset it
        if (end < 0) {
            end = data.length - 1;
        }

        return e;
    }

    public E getFirst() {
        return data[this.start];
    }

    public E getLast() {
        return data[this.end];
    }

    public E[] getData() {
        return data;
    }
    
    /**
     * Same as this.getLast()
     * @return
     */
    public E pop() {
        return this.removeLast();
    }
    
    /**
     * Same as this.addLast()
     * @param element
     */
    public void push(E element) {
        this.addLast(element);
    }

    public static void main(String[] args) {
        MyDeque<Integer> md = new MyDeque<>();
        md.addFirst(5);
        System.out.println(md.removeFirst());
        md.addLast(5);
        System.out.println(md.removeLast());

        for (int i = 0; i < 100; i++) {
            md.addLast(i);
            System.out.format("Adding %d, array: %s%n%n", i, md.toString());
            System.out.format("Size %d%n", md.size());
        }
        for (int i = 0; i < 100; i++) {
            md.addFirst(i);
            System.out.format("Adding %d, array: %s%n%n", i, md.toString());
            System.out.format("Size %d%n", md.size());
        }
        while (md.size() != 0) {
            
            int look = md.getLast();
            int n = md.removeLast();
            assert n == look;
            look = md.getFirst();
            n = md.removeFirst();
            assert n == look;
            
            System.out.format("Removed %d, array: %s%n%n", n, md.toString());
            System.out.format("Size %d%n", md.size());
        }

    }
}
