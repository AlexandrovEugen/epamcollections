package com.epam.java.se;


import java.util.*;


public class CustomHashMap<K, V> implements Map<K, V> {

    private Entry<K, V>[] buckets;
    private int size;
    private Set<K> keySet;
    private Collection<V> values;

    public CustomHashMap() {
        buckets = new Entry[16];
    }

    private int hash(Object key) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12);
        int hashCode = h ^ (h >>> 7) ^ (h >>> 4);
        return hashCode % buckets.length;
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
        int index = hash(key);
        if (buckets[index] == null) {
            return false;
        } else {
            Entry<K, V> entry = buckets[index];
            if (entry.getKey().equals(key)) {
                return true;
            } else {
                while (entry.hasNext()) {
                    if (entry.next.getKey().equals(key)) {
                        return true;
                    } else {
                        entry = entry.next;
                    }
                }
            }
        }
        return false;
    }

    public boolean containsValue(Object value) {
        if (isEmpty()) {
            return false;
        }
        for (Entry<K, V> bucket : buckets) {
            if (bucket == null) {
                continue;
            } else {
                Entry<K, V> entry = bucket;
                if (entry.getValue().equals(value)) {
                    return true;
                } else {
                    while (entry.hasNext()) {
                        if (entry.next.getValue().equals(value)) {
                            return true;
                        } else {
                            entry = entry.next;
                        }
                    }
                }
            }
        }
        return false;
    }

    public V get(Object key) {
        Objects.requireNonNull(key);
        int index = hash(key);
        if (buckets[index] == null) {
            return null;
        } else {
            Entry<K, V> entry = buckets[index];
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            } else {
                while (entry.hasNext()) {
                    if (entry.next.getKey().equals(key)) {
                        return entry.next.getValue();
                    } else {
                        entry = entry.next;
                    }
                }
            }
        }
        return null;
    }

    public V put(K key, V value) {
        Objects.requireNonNull(key);
        V oldValue;
        int index = hash(key);
        System.out.println(index);
        if (buckets[index] == null) {
            buckets[index] = new Entry<>(key, value);
            size++;
        } else {
            Entry<K, V> entry = buckets[index];
            if (entry.getKey().equals(key)) {
                oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
            while (entry.hasNext()) {
                if (entry.getKey().equals(key)) {
                    oldValue = entry.getValue();
                    entry.setValue(value);
                    return oldValue;
                } else {
                    entry = entry.next;
                }
            }
            entry.setNext(new Entry<>(key, value));
            size++;
        }
        return null;
    }

    public V remove(Object key) {
        Objects.requireNonNull(key);
        int index = hash(key);
        V removed;
        if (buckets[index] == null) {
            return null;
        } else {
            if (buckets[index].getKey().equals(key)) {
                if (!buckets[index].hasNext()) {
                    removed = buckets[index].getValue();
                    buckets[index] = null;
                    size--;
                    return removed;
                } else {
                    Entry<K, V> entry = buckets[index];
                    Entry<K, V> parent;
                    while (entry.hasNext()) {
                        parent = entry;
                        entry = entry.next;
                        if (entry.getKey().equals(key)) {
                            removed = entry.getValue();
                            parent.next = entry.next;
                            size--;
                            return removed;
                        }
                    }
                }
            }
        }
        return null;
    }

    public void putAll(Map<? extends K, ? extends V> m) {
    }

    public void clear() {
        buckets = new Entry[16];
        size = 0;
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

    static class Entry<K, V> implements Map.Entry<K, V> {

        private final K key;
        private V value;

        private Entry<K, V> next = null;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private boolean hasNext() {
            return this.next != null;
        }

        private void setNext(Entry<K, V> next) {
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }
}
