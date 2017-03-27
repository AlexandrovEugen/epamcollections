package com.epam.java.se;


import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


public class CustomHashMap<K, V> implements Map<K, V> {

    private final Entry<K, V>[] buckets;
    private int size;

    public CustomHashMap() {
        buckets = new Entry[16];
    }

    public CustomHashMap(int CAPACITY) {
        buckets = new Entry[CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsKey(Object key) {
        if (isEmpty()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (buckets[i].key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(Object value) {
        if (isEmpty()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (buckets[i].value.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public V get(Object key) {
        Objects.requireNonNull(key);
        for (int i = 0; i < size; i++) {
            if (buckets[i].key.equals(key)) {
                return buckets[i].value;
            }
        }
        return null;
    }

    public V put(K key, V value) {
        Objects.requireNonNull(key);
        buckets[size] = new Entry<>(key, value);
        size = size + 1;
        return null;
    }

    public V remove(Object key) {
        Objects.requireNonNull(key);
        int moveIndex = -1;
        int index = -1;
        V removed = null;

        for (int i = 0; i < size; i++) {
            if (buckets[i].key.equals(key)) {
                index = i;
                moveIndex = size - index - 1;
                removed = buckets[i].value;
            }
        }
        if (moveIndex > 0) {
            System.arraycopy(buckets, index + 1, buckets, index, moveIndex);
        }
        buckets[--size] = null;

        return removed;
    }

    public void putAll(Map<? extends K, ? extends V> m) {
    }

    public void clear() {
    }

    public Set<K> keySet() {
        return null;
    }

    public Collection<V> values() {
        return null;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

    private class Entry<K, V> {

        private final K key;
        private V value;

        private Entry<K, V> next = null;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public boolean hasNext() {
            return this.next != null;
        }

        public Entry<K, V> next() {
            return next;
        }

        public void setNext(Entry<K, V> next) {
            this.next = next;
        }
    }
}
