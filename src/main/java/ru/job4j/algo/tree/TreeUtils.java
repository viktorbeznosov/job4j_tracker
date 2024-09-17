package ru.job4j.algo.tree;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TreeUtils<T> {
    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key
     * @param root корень дерева
     * @param key ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<T> current = stack.pop();
            if (key.equals(current.getValue())) {
                return Optional.of(current);
            }
            current.getChildren().forEach(s -> stack.push(s));
        }
        return null;
    }

    private Optional<Node<T>> getParent(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<T> current = stack.pop();
            if (current.getChildren().stream().filter(s -> s.getValue().equals(key)).findFirst().isPresent()) {
                return Optional.of(current);
            }
            current.getChildren().forEach(s -> stack.push(s));
        }
        return null;
    }

    /**
     * Метод обходит дерево root и добавляет к узлу с ключом parent
     * новый узел с ключом child, при этом на момент добавления узел с ключом parent
     * уже должен существовать в дереве, а узла с ключом child в дереве быть не должно
     * @param root корень дерева
     * @param parent ключ узла-родителя
     * @param child ключ узла-потомка
     * @return true если добавление произошло успешно и false в обратном случае.
     * @throws IllegalArgumentException если root является null
     */
    public boolean add(Node<T> root, T parent, T child) {
        Optional<Node<T>> current = findByKey(root, parent);
        if (current != null) {
            current.get().addChild(new Node<T>(child));
            return true;
        }
        return false;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key,
     * при этом из дерева root удаляется все поддерево найденного узла
     * @param root корень дерева
     * @param key ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        Optional<Node<T>> current = findByKey(root, key);
        Optional<Node<T>> parent = getParent(root, key);
        if (current != null && parent != null) {
            parent.get().removeChild(current.get());
        }

        return current;
    }

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
    }
}
