package ru.job4j.algo.tree;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node root;

    public boolean put(T key) {
        boolean result;
        if (Objects.isNull(root)) {
            root = new Node(key);
            result = true;
        } else {
            result = put(root, key);
        }
        return result;
    }

    private boolean put(Node node, T key) {
        if (key.compareTo(node.key) < 0) {
            if (node.getLeft() == null) {
                node.left = new Node(key);
                return true;
            } else {
                return put(node.left, key);
            }
        } else if (key.compareTo(node.key) > 0) {
            if (node.getRight() == null) {
                node.right = new Node(key);
                return true;
            } else {
                return put(node.right, key);
            }
        }
        return false;
    }

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

    public boolean remove(T key) {
        return false;
    }

    public List<T> inSymmetricalOrder() {
        List<T> result = new ArrayList<>();
        Node node = root;
        return inSymmetricalOrder(node, result);
    }

    private List<T> inSymmetricalOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            inSymmetricalOrder(localRoot.left, list);
            list.add(localRoot.key);
            inSymmetricalOrder(localRoot.right, list);
        }
        return list;
    }

    public List<T> inPreOrder() {
        List<T> result = new ArrayList<>();
        Node node = root;
        return inPreOrder(node, result);
    }

    private List<T> inPreOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            list.add(localRoot.key);
            if (localRoot.left != null) {
                inPreOrder(localRoot.left, list);
            }
            inPreOrder(localRoot.right, list);
        }

        return list;
    }

    public List<T> inPostOrder() {
        List<T> result = new ArrayList<>();
        Node node = root;
        return inPostOrder(node, result);
    }

    private List<T> inPostOrder(Node localRoot, List<T> list) {
        if (localRoot != null) {
            if (localRoot.left != null) {
                inPostOrder(localRoot.left, list);
            }
            if (localRoot.right != null) {
                inPostOrder(localRoot.right, list);
            }
        }
        list.add(localRoot.key);
        return list;
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

    @Override
    public String toString() {
        return PrintTree.getTreeDisplay(root);
    }

    private class Node implements VisualNode {
        private T key;
        private Node left;
        private Node right;

        public Node(T key) {
            this.key = key;
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
            return key.toString();
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.put(4);
        tree.put(2);
        tree.put(1);
        tree.put(3);
        tree.put(6);
        tree.put(5);
        tree.put(7);

        System.out.println(tree);
        System.out.println(tree.inSymmetricalOrder());
        System.out.println(tree.inPreOrder());
        System.out.println(tree.inPostOrder());
    }
}