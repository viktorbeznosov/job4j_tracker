package ru.job4j.algo;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

class BankMaxLoadTimeTest {
    @Test
    void whenSimpleCaseThenCorrectMaxLoadTime() {
        List<Integer[]> visitTimes = Arrays.asList(
                new Integer[]{1, 5},
                new Integer[]{2, 6},
                new Integer[]{3, 8},
                new Integer[]{4, 7}
        );

        int[] result = BankMaxLoadTime.findMaxLoadTime(visitTimes);
        assertThat(result).containsExactly(4, 5);
    }

    @Test
    void whenOverlapCaseThenCorrectMaxLoadTime() {
        List<Integer[]> visitTimes = Arrays.asList(
                new Integer[]{1, 10},
                new Integer[]{2, 6},
                new Integer[]{5, 8},
                new Integer[]{7, 12}
        );

        int[] result = BankMaxLoadTime.findMaxLoadTime(visitTimes);
        assertThat(result).containsExactly(5, 6);
    }

    @Test
    void whenNoOverlapCaseThenCorrectMaxLoadTime() {
        List<Integer[]> visitTimes = Arrays.asList(
                new Integer[]{1, 2},
                new Integer[]{3, 4},
                new Integer[]{5, 6}
        );

        int[] result = BankMaxLoadTime.findMaxLoadTime(visitTimes);
        assertThat(result).containsExactly(1, 2);
    }

    @Test
    void whenComplexCaseThenCorrectMaxLoadTime() {
        List<Integer[]> visitTimes = Arrays.asList(
                new Integer[]{1, 3},
                new Integer[]{2, 4},
                new Integer[]{3, 5},
                new Integer[]{4, 6},
                new Integer[]{5, 7},
                new Integer[]{6, 8}
        );

        int[] result = BankMaxLoadTime.findMaxLoadTime(visitTimes);
        assertThat(result).containsExactly(2, 3);
    }
}