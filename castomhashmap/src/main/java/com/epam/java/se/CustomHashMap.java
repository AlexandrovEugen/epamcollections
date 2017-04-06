package com.epam.java.se;


import java.util.*;


public class CustomHashMap<K, V> implements Map<K, V> {

    private Entry<K, V>[] buckets;
    private int size;
    private Collection<V> values = new Values();

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
        return size == 0;
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
        if (buckets[index] == null) {
            buckets[index] = new Entry<>(key, value, index);
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
            entry.setNext(new Entry<>(key, value, index));
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
        Set<? extends Map.Entry<? extends K, ? extends V>> entries = m.entrySet();
        for (Map.Entry<? extends K, ? extends V> entry : entries) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    public void clear() {
        buckets = new Entry[16];
        size = 0;
    }

    public Set<K> keySet() {
        Set<Map.Entry<K, V>> entrySet = entrySet();
        Set<K> keySet = new HashSet<>();
        for (Map.Entry<K, V> entry : entrySet) {
            keySet.add(entry.getKey());
        }
        return keySet;
    }

    public Collection<V> values() {
        return values;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entrySet = new HashSet<>();
        for (Entry<K, V> bucket : buckets) {
            if (bucket != null) {
                Entry<K, V> entry = bucket;
                entrySet.add(entry);
                while (entry.hasNext()) {
                    entry = entry.next;
                    entrySet.add(entry);
                }
            }
        }
        return entrySet;
    }

    static class Entry<K, V> implements Map.Entry<K, V> {

        private final int hashcode;
        private final K key;
        private V value;

        private Entry<K, V> next = null;

        private Entry(K key, V value, int hashcode) {
            Objects.requireNonNull(key);
            this.hashcode = hashcode;
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

        @Override
        public int hashCode() {
            int result = hashcode;
            result = 31 * result + key.hashCode();
            result = 31 * result + value.hashCode();
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Entry<K, V> entry = (Entry<K, V>) o;
            if (key.equals(entry.getKey())) return false;
            if (value.equals(entry.getValue())) return false;
            if (!next.equals(entry.next)) return false;
            return hashcode == entry.hashcode;
        }
    }

    class Values extends AbstractCollection<V> {

        @Override
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        @Override
        public int size() {
            return CustomHashMap.this.size();
        }

        @Override
        public void clear() {
            CustomHashMap.this.clear();
        }

        @Override
        public boolean contains(Object o) {
            return CustomHashMap.this.containsValue(o);
        }
    }


    class ValueIterator extends HashIterator implements Iterator<V> {
        @Override
        public V next() {
            return nextEntry().getValue();
        }
    }

    abstract class HashIterator {
        Entry<K, V> next;

        int index;

        HashIterator() {
            Entry<K, V>[] aBuckets = buckets;
            next = null;
            index = 0;
            if (aBuckets != null && size > 0) {
                next = aBuckets[index];
                while (index < aBuckets.length && next != null) {
                    next = aBuckets[index++];
                }
            }
        }

        public boolean hasNext() {
            return next != null;
        }

        Entry<K, V> nextEntry() {
            Entry<K, V>[] aBucket = buckets;
            Entry<K, V> e = next;
            if (e == null) {
                throw new NoSuchElementException();
            }
            next = e.next;
            if (next == null && aBucket != null) {
                next = aBucket[index];
                while (index < aBucket.length && next != null) {
                    next = aBucket[index++];
                }
            }
            return e;
        }

    }
}
