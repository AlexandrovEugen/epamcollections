package com.epam.java.se;

import java.awt.*;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


public class CustomTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    private int size = 0;
    private Node<K, V> root = null;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return addNode(root, key, value);
    }

    private V addNode(Node<K, V> node, K key, V value) {
        if (node == null){
            node = new Node<>(key, value);
            node.setColor(Color.RED);
            size++;
            return null;
        }
        else if (node.getKey().compareTo(key) < 0){
            addNode(node.right, key, value);
        }
        else if (node.getKey().compareTo(key) > 0){
            addNode(node.left, key, value);
        }
        else {
            V oldValue = node.getValue();
            node.setValue(value);
            return oldValue;
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    private static class Node<K extends Comparable<K>, V> implements Map.Entry<K, V> {

        private Node<K, V> left;
        private Node<K, V> right;

        private final K key;
        private V value;
        private Color color;

        private Node(K key, V value) {
            Objects.requireNonNull(key);
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }
    }
}
