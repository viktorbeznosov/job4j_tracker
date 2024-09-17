package ru.job4j.algo.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node<E> {
    private E value;
    private List<Node<E>> children = new ArrayList<>();

    public Node(E value) {
        this.value = value;
    }

    @SafeVarargs
    public Node(E value, Node<E>... children) {
        this.value = value;
        this.children.addAll(Arrays.asList(children));
    }

    @Override
    public String toString() {
        return String.format("Node{ %s }", value);
    }

    public E getValue() {
        return value;
    }

    public List<Node<E>> getChildren() {
        return children;
    }

    public void addChild(Node<E> child) {
        this.children.add(child);
    }

    public void removeChild(Node<E> child) {
        this.children.remove(child);
    }
}
