package com.epam.java.se;

import java.awt.*;
import java.util.*;
import java.util.List;


public class CustomTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    private int size = 0;
    private Node<K, V> root = null;
    private Set<Map.Entry<K, V>> entrySet;
    private Set<K> keySet;

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
        Node<K, V> node = findNode(root, (K) key);
        return node != null;
    }

    @Override
    public boolean containsValue(Object value) {
        for (Entry<K, V> entry : getOrderedNodes()) {
            if (entry.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        Objects.requireNonNull(key);
        return findNode(root, (K) key).getValue();
    }

    private Node<K, V> findNode(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        } else if (node.getKey().compareTo(key) < 0) {
            return findNode(node.right, key);
        } else if (node.getKey().compareTo(key) > 0) {
            return findNode(node.left, key);
        } else {
            return node;
        }
    }

    @Override
    public V put(K key, V value) {
        return addNode(root, key, value);
    }

    private V addNode(Node<K, V> node, K key, V value) {
        if (root == null) {
            root = new Node<>(key, value);
            root.setColor(Color.RED);
            size++;
            root.parent = null;
            return null;
        } else if (node.getKey().compareTo(key) < 0) {
            if (node.right == null) {
                node.right = new Node<>(key, value);
                node.right.setColor(Color.RED);
                node.right.parent = node;
                size++;
            } else {
                addNode(node.right, key, value);
            }
        } else if (node.getKey().compareTo(key) > 0) {
            if (node.left == null) {
                node.left = new Node<>(key, value);
                node.left.setColor(Color.RED);
                node.left.parent = node;
                size++;
            } else {
                addNode(node.left, key, value);
            }
        } else {
            V oldValue = node.getValue();
            node.setValue(value);
            return oldValue;
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        Objects.requireNonNull(key);
        if (root.getKey().compareTo((K) key) == 0) {
            if (root.right == null && root.left == null) {
                V removedValue = root.getValue();
                root = null;
                size--;
                return removedValue;
            } else if (root.left == null) {
                V removedValue = root.getValue();
                root = root.right;
                size--;
                return removedValue;
            } else if (root.right == null) {
                V removeValue = root.getValue();
                root = root.left;
                size--;
                return removeValue;
            } else {
                Node<K, V> max = max(root.left);
                V removedValue = root.getValue();
                max.left = root.left;
                max.right = root.right;
                root = max;
                max.parent.right = null;
                size--;
                return removedValue;
            }
        } else {
            Node<K, V> removedNode = findNode(root, (K) key);
            if (removedNode == null) {
                return null;
            } else if (removedNode.left == null && removedNode.right == null) {
                Node<K, V> parent = removedNode.parent;
                if (parent.getKey().compareTo(removedNode.getKey()) < 0) {
                    V removedValue = removedNode.getValue();
                    parent.right = null;
                    size--;
                    return removedValue;
                } else {
                    V removedValue = removedNode.getValue();
                    parent.left = null;
                    size--;
                    return removedValue;
                }
            } else if (removedNode.left == null) {
                Node<K, V> parent = removedNode.parent;
                if (parent.getKey().compareTo(removedNode.getKey()) < 0) {
                    V removedValue = removedNode.getValue();
                    parent.right = removedNode.right;
                    size--;
                    return removedValue;
                } else {
                    V removedValue = removedNode.getValue();
                    parent.left = removedNode.right;
                    size--;
                    return removedValue;
                }
            } else if (removedNode.right == null) {
                Node<K, V> parent = removedNode.parent;
                if (parent.getKey().compareTo(removedNode.getKey()) < 0) {
                    V removedValue = removedNode.getValue();
                    parent.right = removedNode.left;
                    size--;
                    return removedValue;
                } else {
                    V removedValue = removedNode.getValue();
                    parent.left = removedNode.left;
                    size--;
                    return removedValue;
                }
            } else {
                Node<K, V> parent = removedNode.parent;
                Node<K, V> max = max(removedNode.left);
                V removedValue = removedNode.getValue();
                if (parent.getKey().compareTo(removedNode.getKey()) < 0) {
                    max.left = removedNode.left;
                    max.right = removedNode.right;
                    parent.right = max;
                    max.parent.right = null;
                    size--;
                    return removedValue;
                } else {
                    max.left = removedNode.left;
                    max.right = removedNode.right;
                    parent.left = max;
                    max.parent.right = null;
                    size--;
                    return removedValue;
                }
            }
        }
    }

    private Node<K, V> max(Node<K, V> node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        keySet = new HashSet<>();
        for (Entry<K, V> node : getOrderedNodes()) {
            keySet.add(node.getKey());
        }
        return keySet;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        entrySet = new HashSet<>(getOrderedNodes());
        return entrySet;
    }

    private void orderedTreeWalk(Node<K, V> node, List<Map.Entry<K, V>> list) {
        if (node != null) {
            orderedTreeWalk(node.left, list);
            list.add(node);
            orderedTreeWalk(node.right, list);
        }
    }

    private List<Map.Entry<K, V>> getOrderedNodes() {
        final List<Map.Entry<K, V>> entry = new ArrayList<>();
        orderedTreeWalk(root, entry);
        return entry;
    }

    private static class Node<K extends Comparable<K>, V> implements Map.Entry<K, V> {

        private Node<K, V> left;
        private Node<K, V> right;
        private Node<K, V> parent;

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
