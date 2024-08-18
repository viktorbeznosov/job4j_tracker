package ru.job4j.algo.sliding.window;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    private static int minLength(List<Interval> intervals) {
        Interval minInterval = intervals.stream()
            .min(Comparator.comparing(interval -> interval.end - interval.start))
            .get();
        return minInterval.end - minInterval.start;
    }

    private static int maxLength(List<Interval> intervals) {
        Interval minInterval = intervals.stream()
                .max(Comparator.comparing(interval -> interval.end - interval.start))
                .get();
        return minInterval.end - minInterval.start;
    }

    private static int start(List<Interval> intervals) {
        return intervals.stream()
                .min(Comparator.comparing(interval -> interval.start))
                .get()
                .start;
    }

    private static int end(List<Interval> intervals) {
        return intervals.stream()
                .max(Comparator.comparing(interval -> interval.end))
                .get()
                .end;
    }

    private static boolean intersect(Interval first, Interval second) {
        return first.start < second.end && first.start >= second.start
                || first.end <= second.end && first.end > second.start;
    }

    public static int[] findMaxOverlapInterval(List<Interval> intervals) {
        if (intervals.isEmpty()) {
            return new int[]{-1, -1};
        }

        int count = start(intervals);
        int end = end(intervals);
        int minLength = minLength(intervals);
        int maxLength = maxLength(intervals);
        int maxIntersectsCount = 0;
        Interval resultInterval = new Interval(-1, 1);
        for (int length = minLength; length <= maxLength; length++) {
            while (count + length <= end) {
                Interval slidingWindow = new Interval(count, count + length);
                AtomicInteger intersects = new AtomicInteger();
                intervals.forEach(interval -> {
                    if (intersect(slidingWindow, interval)) {
                        intersects.getAndIncrement();
                    }
                });
                if (intersects.get() > maxIntersectsCount) {
                    maxIntersectsCount = intersects.get();
                    resultInterval = slidingWindow;
                }
                count++;
            }
        }

        return new int[]{resultInterval.start, resultInterval.end};
    }

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 10));
        intervals.add(new Interval(2, 3));
        intervals.add(new Interval(4, 5));
        intervals.add(new Interval(6, 7));
        intervals.add(new Interval(8, 9));

        int[] result = findMaxOverlapInterval(intervals);

        System.out.println("Interval that overlaps the maximum number of intervals: [" + result[0] + ", " + result[1] + "]");
    }
}