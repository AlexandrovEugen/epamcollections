import java.util.*;

public class CustomLinkedList<E> implements List<E> {


    private int size = 0;
    private Node<E> head = new Node<>(null);
    private Node<E> tail = new Node<>(null);

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
        Node<E> node = head;
        while (node.hasNext()) {
            node = node.next;
            if (node.getElem() == null) {
                if (o == null) {
                    return true;
                }
            } else if (node.getElem().equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        /// TODO: 05.04.2017
        return null;
    }

    @Override
    public E[] toArray() {
        Node<E> iterator = head.next;
        E[] array = (E[]) new Object[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = iterator.getElem();
            iterator = iterator.next;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E t) {
        Node<E> iterator = head;
        while (iterator.hasNext()) {
            iterator = iterator.next;
        }
        iterator.next = new Node<>(t);
        size++;
        return false;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> current = head.next;
        Node<E> prev = head;
        while (current != null) {
            if (o.equals(current.getElem())) {
                prev.next = current.next;
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (Object e : c) {
            add((E) e);
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object e : c) {
            if (contains(e)) {
                remove(e);
            }
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        /// TODO: 05.04.2017
        return true;
    }

    @Override
    public void clear() {
        head.next = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return getNodeByIndex(index).getElem();
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        } else {
            Node<E> iterator = head.next;
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    E oldElem = iterator.getElem();
                    iterator.setElem(element);
                    return oldElem;
                } else {
                    iterator = iterator.next;
                }
            }
        }
        return null;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        } else {
            Node<E> iterator = head.next;
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    iterator.setElem(element);
                } else {
                    iterator = iterator.next;
                }
            }
        }
    }

    @Override
    public E remove(int index) {
        Node<E> current = getNodeByIndex(index - 1);
        size--;
        E value = current.next.getElem();
        current.next = current.next.next;

        return value;
    }

    private Node<E> getNodeByIndex(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> current = head.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    @Override
    public int indexOf(Object o) {
        Node<E> iterator = head.next;
        int index = 0;
        while (iterator != null) {
            if (iterator.getElem().equals(o)) {
                return index;
            } else {
                iterator = iterator.next;
                index++;
            }

        }
        throw new NoSuchElementException();
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        Node<E> iterator = head.next;
        for (int i = 0; i < size; i++) {
            if (iterator.getElem().equals(o)) {
                index = i;
                iterator = iterator.next;
            } else {
                iterator = iterator.next;
            }
        }
        if (index == -1){
            throw new NoSuchElementException();
        }
        return index;
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
        List<E> list = new CustomLinkedList<>();
        if (fromIndex < 0 || fromIndex <= size) {
            throw new IllegalArgumentException();
        } else if (toIndex < 0 || toIndex <= size) {
            throw new IllegalArgumentException();
        } else {
            int index = 0;
            Node<E> iterator = head.next;
            while (iterator != null) {
                if (index >= fromIndex && index <= toIndex) {
                    add(iterator.getElem());
                    index++;
                    iterator = iterator.next;
                } else {
                    index++;
                    iterator = iterator.next;
                }

            }
        }
        return list;
    }

    private static class Node<E> {
        private E elem;
        private Node<E> next = null;
        private Node<E> prev = null;

        private Node(E elem) {
            this.elem = elem;
        }

        private E getElem() {
            return elem;
        }

        private void setElem(E elem) {
            this.elem = elem;
        }

        private boolean hasNext() {
            return next != null;
        }

        public Node<E> next() {
            return next;
        }

        public Node<E> prev() {
            return prev;
        }
    }
}
