package ru.job4j.tracker;

import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ShowByIdTest {
    @Test
    public void whenShowById() {
        Store tracker = new MemTracker();
        Output output = new StubOutput();
        Item item = tracker.add(new Item("New item"));
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(item.getId());
        UserAction showById = new ShowById(output);
        showById.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find item by id ===" + ln
                + item + ln
        );
    }
}