package ru.job4j.algo.tree;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.*;

class BinarySearchTreeTest {
    @Test
    void whenAddToEmptyTreeThenListContainsOneElement() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertThat(tree.put("first")).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(1)
                .containsExactly("first");
    }

    @Test
    void whenAddTwoToEmptyTreeThenListContainsTwoElement() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertThat(tree.put("first")).isTrue();
        assertThat(tree.put("second")).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(2)
                .containsExactly("first", "second");
    }

    @Test
    void whenAddElementThenContainElementOk() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.put("first");
        tree.put("second");
        tree.put("three");
        assertThat(tree.contains("second")).isTrue();
        assertThat(tree.contains("four")).isFalse();
    }

    @Test
    void whenAddMaximumNotEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 8, 7}) {
            tree.put(element);
        }
        assertThat(tree.maximum()).isEqualTo(8);
    }

    @Test
    void whenAddMaximumIsEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            tree.put(element);
        }
        assertThat(tree.maximum()).isEqualTo(7);
    }

    @Test
    void whenAddMinimumIsEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.minimum()).isEqualTo(1);
    }

    @Test
    void whenAddMinimumIsNotEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7 }) {
            tree.put(element);
        }
        assertThat(tree.minimum()).isEqualTo(2);
    }

    @Test
    void whenSymmetricalOrderThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.inSymmetricalOrder()).hasSize(7)
                .containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenPreOrderThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.inPreOrder()).hasSize(7)
                .containsExactly(4, 2, 1, 3, 6, 5, 7);
    }

    @Test
    void whenPostOrderThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.inPostOrder()).hasSize(7)
                .containsExactly(1, 3, 2, 5, 7, 6, 4);
    }

    @Test
    void testRemoveRightOfRoot() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{5, 3, 10, 2, 4, 9, 12}) {
            tree.put(element);
        }
        tree.remove(10);
        assertThat(tree.inSymmetricalOrder())
                .hasSize(6)
                .containsExactly(2, 3, 4, 5, 9, 12);
        assertThat(tree.contains(10)).isFalse();
    }

    @Test
    void testRemoveLeftOfRoot() throws NoSuchFieldException {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{5, 3, 10, 2, 4, 9, 12}) {
            tree.put(element);
        }
        tree.remove(3);
        assertThat(tree.inSymmetricalOrder())
                .hasSize(6)
                .containsExactly(2, 4, 5, 9, 10, 12);
    }
}