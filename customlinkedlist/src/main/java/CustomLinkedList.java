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

        return new Iterator<E>() {
            private Node<E> iterator = head;

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public E next() {
                iterator = iterator.next;
                return iterator.getElem();
            }
        };
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
        if (a.length < size) {
            throw new IllegalArgumentException();
        } else {
            Node<E> iterator = head.next;
            for (int i = 0; i < size; i++) {
                a[i] = (T) iterator.getElem();
                iterator = iterator.next;
            }
        }
        return a;
    }

    @Override
    public boolean add(E e) {
        Node<E> iterator = head;
        while (iterator.hasNext()) {
            iterator = iterator.next;
        }
        iterator.next = new Node<>(e);
        iterator.next.prev = iterator;
        size++;
        return iterator.hasNext();
    }

    @Override
    public boolean remove(Object o) {
        Node<E> current = head;
        Node<E> prev = head;
        while (current.hasNext()) {
            current = current.next;
            if (o.equals(current.getElem())) {
                prev.next = current.next;
                size--;
                return true;
            }
            prev = current;
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
        if (c.isEmpty()) {
            return false;
        }
        for (Object e : c) {
            add((E) e);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        Objects.requireNonNull(c);
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (c.isEmpty()) {
            return false;
        }
        if (isEmpty()) {
            addAll(c);
        }
        Node<E> iterator = getNodeByIndex(index);
        for (Object e : c) {
            if (iterator != null) {
                iterator.setElem((E) e);
                iterator = iterator.next;
            } else {
                iterator = new Node<>((E) e);
                iterator = iterator.next;
                size++;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        if (c.isEmpty()) {
            return false;
        }
        for (Object e : c) {
            if (contains(e)) {
                remove(e);
            }
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (isEmpty()) {
            return false;
        }
        List<E> toRemove = new CustomLinkedList<>();
        for (Object e : c) {
            if (contains(e)) {
                toRemove.add((E) e);
            }
        }
        clear();
        for (E e : toRemove) {
            add(e);
        }
        return toRemove.isEmpty();
    }

    @Override
    public void clear() {
        head.next = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index < 0 || index > size) {
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
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
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
        if (index == -1) {
            throw new NoSuchElementException();
        }
        return index;
    }

    @Override
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
        }
        return new ListIterator<E>() {
            private Node<E> iterator = getNodeByIndex(index);
            private Node<E> previous = iterator.prev;
            private int i = index;


            @Override
            public boolean hasNext() {
                return !isEmpty() && iterator.hasNext();
            }

            @Override
            public E next() {
                previous = iterator;
                iterator = iterator.next;
                i++;
                return iterator.next.getElem();
            }

            @Override
            public boolean hasPrevious() {
                return previous != head && previous.hasPrevious();
            }

            @Override
            public E previous() {
                previous = previous.prev;
                return previous.getElem();
            }

            @Override
            public int nextIndex() {
                return i + 1;
            }

            @Override
            public int previousIndex() {
                return i - 1;
            }

            @Override
            public void remove() {
                CustomLinkedList.this.remove(iterator.elem);
            }

            @Override
            public void set(E e) {
                CustomLinkedList.this.set(i, iterator.elem);
            }

            @Override
            public void add(E e) {
                CustomLinkedList.this.add(e);
            }
        };
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        List<E> list = new CustomLinkedList<>();
        if (isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (fromIndex < 0 || fromIndex > size) {
            throw new IllegalArgumentException();
        } else if (toIndex < 0 || toIndex > size) {
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

        private boolean hasPrevious() {
            return prev != null;
        }

        @Override
        public int hashCode() {
            int result = elem.hashCode();
            result = 31 * result;
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<E> node = (Node<E>) o;
            if (!elem.equals(node.getElem())) return false;
            if (!prev.equals(node.prev)) return false;
            if (!next.equals(node.next)) return false;
            return hashCode() == node.hashCode();
        }


    }
}
