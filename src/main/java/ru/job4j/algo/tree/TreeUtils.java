package ru.job4j.algo.tree;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TreeUtils<T> {

    /**
     * Метод выполняет обход дерева и считает количество узлов
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        AtomicInteger result = new AtomicInteger();
        findAll(root).forEach(s -> result.getAndIncrement());
        return result.get();
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
    public Iterable<T> findAll(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        Queue<Node<T>> queue = new ArrayDeque<>();
        List<T> keys = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            keys.add(current.getValue());
            current.getChildren().forEach(s -> queue.add(s));
        }
        return keys;
    }

    public static void main(String[] args) {
        Node<Integer> root = new Node(0, new Node(1, new Node(3), new Node(4)), new Node(2, new Node(5), new Node(6)));
        TreeUtils<Integer> treeUtils = new TreeUtils<>();
        System.out.println(treeUtils.countNode(root));
    }
}
