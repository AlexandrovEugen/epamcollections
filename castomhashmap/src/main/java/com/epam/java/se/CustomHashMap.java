package com.epam.java.se;


import java.util.*;


public class CustomHashMap<K, V> implements Map<K, V> {

    private final Entry<K, V>[] buckets;
    private int size;
    private Set<K> keySet = new HashSet<>();
    private Collection<V> values;

    public CustomHashMap() {
        buckets = new Entry[16];
    }

    private static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
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
            if (entry.key.equals(key)) {
                return true;
            } else {
                while (entry.hasNext()) {
                    if (entry.next.key.equals(key)) {
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
                if (entry.value.equals(value)) {
                    return true;
                } else {
                    while (entry.hasNext()) {
                        if (entry.next.value.equals(value)) {
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
            if (entry.key.equals(key)) {
                return entry.value;
            } else {
                while (entry.hasNext()) {
                    if (entry.next.key.equals(key)) {
                        return entry.next.value;
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
        int index = hash(key);
        if (buckets[index] == null) {
            buckets[index] = new Entry<>(key, value);
            size++;
        } else {
            Entry<K, V> entry = buckets[index];
            while (entry.hasNext()) {
                entry = entry.next;
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
            if (buckets[index].key.equals(key)) {
                if (!buckets[index].hasNext()) {
                    removed = buckets[index].value;
                    buckets[index] = null;
                    size--;
                    return removed;
                } else {
                    Entry<K, V> entry = buckets[index];
                    Entry<K, V> parent;
                    while (entry.hasNext()) {
                        parent = entry;
                        entry = entry.next;
                        if (entry.key.equals(key)) {
                            removed = entry.value;
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

        public void setNext(Entry<K, V> next) {
            this.next = next;
        }
    }
}
