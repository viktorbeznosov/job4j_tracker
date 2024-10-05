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
        boolean result = false;
        if (Objects.nonNull(key) && Objects.nonNull(root)) {
            result = remove(root, key);
        }
        return result;
    }

    private boolean remove(Node source, T key) {
        boolean result = true;
        Node current = source;
        Node parent = source;
        boolean isLeft = true;
        while (result && !Objects.equals(current.key, key)) {
            parent = current;
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                isLeft = true;
                current = current.left;
            } else if (cmp > 0) {
                isLeft = false;
                current = current.right;
            }
            if (Objects.isNull(current)) {
                result = false;
            }
        }
        if (result) {
            if (Objects.isNull(current.left) && Objects.isNull(current.right)) {
                swap(isLeft, source, parent, current, null);
            } else if (Objects.nonNull(current.left) && Objects.isNull(current.right)) {
                swap(isLeft, source, parent, current, current.left);
            } else if (Objects.isNull(current.left)) {
                swap(isLeft, source, parent, current, current.right);
            } else {
                Node heir = getHeir(current);
                swap(isLeft, source, parent, current, heir);
                heir.left = current.left;
            }
            current.key = null;
            current.left = null;
            current.right = null;
        }
        return result;
    }

    private void swap(boolean isLeft, Node source, Node parent, Node current, Node equal) {
        if (Objects.equals(current, source)) {
            root = equal;
        } else if (isLeft) {
            parent.left = equal;
        } else {
            parent.right = equal;
        }
    }

    private Node getHeir(Node delNode) {
        Node nodeParent = delNode;
        Node node = delNode;
        Node current = delNode.right;
        while (current != null) {
            nodeParent = node;
            node = current;
            current = current.left;
        }
        if (node != delNode.right) {
            nodeParent.left = node.right;
            node.right = delNode.right;
        }
        return node;
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

    public void clear() {
        Node node = root;
        clear(node);
    }

    private void clear(Node first) {
        if (first != null) {
            if (first.left != null) {
                clear(first.left);
                first.left = null;
            }
            if (first.right != null) {
                clear(first.right);
                first.right = null;
            }
        }
        if (!first.key.equals(root.key)) {
            first.key = null;
        }
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
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] array = new int[]{2, 1, 10, 6, 14};
        for (int i : array) {
            bst.put(i);
        }
        System.out.println(bst);
        System.out.println(bst.remove(3));
        System.out.println("После удаления узла 10 :");
        System.out.println(bst.inPostOrder());
        bst.clear();
    }
}