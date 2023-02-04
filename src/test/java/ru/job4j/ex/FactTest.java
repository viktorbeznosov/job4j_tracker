package ru.job4j.ex;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FactTest {
    @Test
    public void whenException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    (new Fact()).calc(-1);
                }
        );
        assertThat(exception.getMessage()).isEqualTo("n should be more than zero.");
    }

    @Test
    public void when3ThenFact6() {
        int expected = 6;
        int result = (new Fact()).calc(3);
        assertThat(result).isEqualTo(expected);
    }
}