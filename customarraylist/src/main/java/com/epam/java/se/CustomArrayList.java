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
    public Object[] toArray() {
        return new Object[0];
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

    private void ensureCapacity() {
        int newLength = (array.length * 3) / 2 + 1;
        array = Arrays.copyOf(array, newLength);
    }

    @Override
    public boolean remove(Object o) {
        try {
            remove(indexOf(o));
        }
        catch (NoSuchElementException e){
            return false;
        }
        return true;
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
        if (index < 0 || index >= size){
            throw new IllegalArgumentException();
        }
        else {
            E oldValue = array[index];
            array[index] = element;
            return oldValue;
        }
    }

    @Override
    public void add(int index, E element) {
    }

    @Override
    public E remove(int index) {
        int length = array.length - index;
        E value = array[index];
        System.arraycopy(array, index + 1, array, index, length-1);
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
        List<E> sublist = new CustomArrayList<>();
        if (fromIndex < 0 || fromIndex >= size){
            throw  new IllegalArgumentException();
        }
        if (toIndex < 0 || toIndex >= size){
            throw new IllegalArgumentException();
        }
        sublist.addAll(Arrays.asList(array).subList(fromIndex, toIndex + 1));
        return sublist;
    }
}
