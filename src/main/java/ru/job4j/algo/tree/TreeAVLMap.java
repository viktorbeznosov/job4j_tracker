package ru.job4j.algo.tree;

import java.util.*;
import java.util.stream.Collectors;

public class TreeAVLMap<T extends Comparable<T>, V> {

    private Node root;

    public boolean contains(T key) {
        if (find(root, key) == null) {
            return false;
        }
        return key.equals(find(root, key).key);
    }

    private Node find(Node node, T key) {
        if (node == null) {
            return null;
        }
        if (key.equals(node.key)) {
            return node;
        }
        return key.compareTo(node.key) > 0 ? find(node.right, key) : find(node.left, key);
    }

    public boolean put(T key, V value) {
        boolean result = false;
        if (Objects.nonNull(key) && contains(key)) {
            Node node = find(root, key);
            node.value = value;
            result = true;
        } else {
            root = put(root, key, value);
            result = true;
        }
        return result;
    }

    private Node put(Node node, T key, V value) {
        Node result = new Node(key, value);
        if (Objects.nonNull(node)) {
            int comparisonResult = key.compareTo(node.key);
            if (comparisonResult < 0) {
                node.left = put(node.left, key, value);
            } else {
                node.right = put(node.right, key,  value);
            }
            updateHeight(node);
            result = balance(node);
        }
        return result;
    }

    private void updateHeight(Node node) {
        int leftNodeHeight = Objects.isNull(node.left) ? -1 : node.left.height;
        int rightNodeHeight = Objects.isNull(node.right) ? -1 : node.right.height;
        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
        node.balanceFactor = rightNodeHeight - leftNodeHeight;
    }

    private Node balance(Node node) {
        Node result = node;
        if (node.balanceFactor < -1) {
            if (node.left.balanceFactor >= 0) {
                result = leftRightCase(node);
            } else {
                result = rightRotation(node);
            }
        } else if (node.balanceFactor > 1) {
            if (node.right.balanceFactor >= 0) {
                result = leftRotation(node);
            } else {
                result = rightLeftCase(node);
            }
        }
        return result;
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
    }

    private Node leftRotation(Node node) {
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        updateHeight(node);
        updateHeight(newParent);

        return newParent;
    }

    private Node rightRotation(Node node) {
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        updateHeight(node);
        updateHeight(newParent);

        return newParent;
    }

    public boolean remove(T key) {
        boolean result = false;
        if (Objects.nonNull(key) && Objects.nonNull(root) && contains(key)) {
            root = remove(root, key);
            result = true;
        }
        return result;
    }

    private Node remove(Node node, T key) {
        if (node == null) {
            return null;
        }
        int comparisonResult = key.compareTo(node.key);
        if (comparisonResult < 0) {
            node.left = remove(node.left, key);
        } else if (comparisonResult > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                if (node.left.height > node.right.height) {
                    T heir = maximum(node.left).key;
                    node.key = heir;
                    node.left = remove(node.left, heir);
                } else {
                    T heir = minimum(node.right).key;
                    node.key = heir;
                    node.right = remove(node.right, heir);
                }
            }
        }
        updateHeight(node);
        return balance(node);
    }

    public T minimum() {
        return Objects.nonNull(root) ? minimum(root).key : null;
    }

    private Node minimum(Node node) {
        if (node.left != null) {
            node = minimum(node.left);
        }
        return node;
    }

    public T maximum() {
        return Objects.nonNull(root) ? maximum(root).key : null;
    }

    private Node maximum(Node node) {
        if (node.right != null) {
            node = maximum(node.right);
        }
        return node;
    }

    public V get(T key) {
        Node node = find(root, key);
        return node != null ? node.value : null;
    }

    public Set<T> keySet() {
        return inSymmetricalOrder()
                .stream()
                .map(s -> s.key)
                .collect(Collectors.toSet());
    }

    public Collection<V> values() {
        return inSymmetricalOrder()
                .stream()
                .map(s -> s.value)
                .collect(Collectors.toList());
    }

    private List<Node> inSymmetricalOrder() {
        List<Node> result = new ArrayList<>();
        Node node = root;
        return inSymmetricalOrder(node, result);
    }

    private List<Node> inSymmetricalOrder(Node localRoot, List<Node> list) {
        if (localRoot != null) {
            inSymmetricalOrder(localRoot.left, list);
            list.add(localRoot);
            inSymmetricalOrder(localRoot.right, list);
        }
        return list;
    }

    @Override
    public String toString() {
        return PrintTree.getTreeDisplay(root);
    }

    private class Node implements VisualNode {
        private int balanceFactor;
        private T key;
        private V value;
        private int height;
        private Node left;
        private Node right;

        Node(T key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public VisualNode getLeft() {
            return left;
        }

        @Override
        public VisualNode getRight() {
            return right;
        }

        @Override
        public String getText() {
            return String.valueOf(key + " " + value);
        }
    }

    public static void main(String[] args) {
        TreeAVLMap<Integer, String> map = new TreeAVLMap<>();
        map.put(1, "11");
        map.put(2, "22");
        map.put(3, "33");
        map.put(4, "44");
        map.put(5, "55");
        map.put(6, "66");
        map.put(7, "77");
        map.put(5, "555");
        System.out.println(map);
        System.out.println(map.values());
        System.out.println(map.keySet());
    }
}
