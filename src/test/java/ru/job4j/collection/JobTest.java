package ru.job4j.collection;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.assertj.core.api.Assertions.assertThat;

public class JobTest {
    @Test
    public void whenCompareAscByName() {
        Comparator<Job> cmpAscByName = new JobAscByName();
        int rsl = cmpAscByName.compare(
                new Job("First Job", 1),
                new Job("Second Job", 2)
        );
        assertThat(rsl).isLessThan(0);
    }

    @Test
    public void whenCompareDescByName() {
        Comparator<Job> cmpAscByName = new JobDescByName();
        int rsl = cmpAscByName.compare(
                new Job("First Job", 1),
                new Job("Second Job", 2)
        );
        assertThat(rsl).isGreaterThan(0);
    }

    @Test
    public void whenCompareAscByPriority() {
        Comparator<Job> cmpAscByName = new JobAscByPriority();
        int rsl = cmpAscByName.compare(
                new Job("First Job", 1),
                new Job("Second Job", 2)
        );
        assertThat(rsl).isLessThan(0);
    }

    @Test
    public void whenCompareDescByPriority() {
        Comparator<Job> cmpAscByName = new JobDescByPriority();
        int rsl = cmpAscByName.compare(
                new Job("First Job", 1),
                new Job("Second Job", 2)
        );
        assertThat(rsl).isGreaterThan(0);
    }

    @Test
    public void whenCompatorByNameAndPrority() {
        Comparator<Job> cmpNamePriority = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl).isLessThan(0);
    }

    @Test
    public void whenCompareDescByNameAndPriority() {
        Comparator<Job> cmpDescByNameAndPrioriry = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpDescByNameAndPrioriry.compare(
                new Job("Impl task", 0),
                new Job("Impl task", 1)
        );
        assertThat(rsl).isGreaterThan(0);
    }
}