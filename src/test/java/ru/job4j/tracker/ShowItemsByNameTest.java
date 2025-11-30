package ru.job4j.tracker;

import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ShowItemsByNameTest {
    @Test
    public void whenShowItemsByName() {
        Store tracker = new MemTracker();
        Output output = new StubOutput();
        Item item = tracker.add(new Item("New item"));
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(item.getName());
        UserAction showItemsByName = new ShowItemsByName(output);
        showItemsByName.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Find items by name ===" + ln
                        + item + ln
        );
    }
}