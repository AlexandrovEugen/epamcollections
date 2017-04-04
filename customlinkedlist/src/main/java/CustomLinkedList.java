import java.util.*;

public class CustomLinkedList<E> implements List<E> {


    private int size = 0;
    private Node<E> head;
    private Node<E> current;


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (!head.hasNext()) {
            return false;
        } else {
            Node<E> node = head.next;
            while (node != null) {
                if (node.getElem().equals(o)) {
                    return true;
                } else {
                    node = node.next;
                }
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        if (head.next == null) {
            head.next = new Node<>(head, e, head);
            current = head.next;
            size++;
            return true;
        } else {
            current.next = new Node<>(current.previous, e, head);
            current = current.next;
            size++;
            return true;
        }
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    private static class Node<E> {
        private E elem;
        private Node<E> next = null;
        private Node<E> previous = null;

        public Node(Node<E> previous, E elem, Node<E> next) {
            this.previous = previous;
            this.elem = elem;
            this.next = next;
        }

        public E getElem() {
            return elem;
        }

        public void setElem(E elem) {
            this.elem = elem;
        }

        public boolean hasNext() {
            return next != null;
        }

        public Node<E> next() {
            return next;
        }
    }
}
