package com.epam.java.se;

import java.util.*;


public class CustomArrayList<E> implements List<E> {

    private int capacity = 10;
    private int size;
    private E[] array = (E[]) new Object[capacity];

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
        if (isEmpty()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (array[i] == null) {
                if (o == null) {
                    return true;
                }
            } else if (array[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                return array[index++];
            }
        };
    }

    @Override
    public E[] toArray() {
        E[] copy = (E[]) new Object[size];
        System.arraycopy(array, 0, copy, 0, size);
        return copy;

    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        if (size + 1 == array.length) {
            ensureCapacity();
        }
        array[size++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        try {
            remove(indexOf(o));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e: c) {
            if (!contains(e)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (this.isEmpty()) {
            array = Arrays.copyOf((E[]) c.toArray(), c.size());
            size = c.size();
        }
        else {
            for (Object e : c.toArray()) {
                add((E) e);
            }
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Object[] a = c.toArray();
        int numNew = a.length;
        if (array.length < numNew){
            ensureCapacity();
        }
        int numMoved = size - index;
        if (numMoved > 0)
            System.arraycopy(array, index, array, index + numNew,
                    numMoved);

        System.arraycopy(a, 0, array, index, numNew);
        size += numNew;
        return numNew != 0;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object e : c.toArray()) {
            if (contains(e)) {
                remove(e);
            }
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (int i = 0; i < size; i++) {
            if (!c.contains(array[i])){
                remove(i);
            }
        }
        return false;
    }

    @Override
    public void clear() {
        array = (E[]) new Object[10];
        size = 0;
    }

    @Override
    public E get(int index) {
        if (size <= index || size < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return array[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        } else {
            E oldValue = array[index];
            array[index] = element;
            return oldValue;
        }
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        } else {
            array[index] = element;
        }
    }

    @Override
    public E remove(int index) {
        int length = array.length - index;
        E value = array[index];
        System.arraycopy(array, index + 1, array, index, length - 1);
        size--;
        return value;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (array[i] == null) {
                if (o == null) {
                    return i;
                }
            } else {
                if (array[i].equals(o)) {
                    return i;
                }
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(o)) {
                return i;
            }
        }
        throw new NoSuchElementException();
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
        List<E> sublist = new CustomArrayList<>();
        if (fromIndex < 0 || fromIndex >= size) {
            throw new IllegalArgumentException();
        }
        if (toIndex < 0 || toIndex >= size) {
            throw new IllegalArgumentException();
        }
        sublist.addAll(Arrays.asList(array).subList(fromIndex, toIndex + 1));
        return sublist;
    }

    private void ensureCapacity() {
        int newLength = (array.length * 3) / 2 + 1;
        array = Arrays.copyOf(array, newLength);
    }
}
