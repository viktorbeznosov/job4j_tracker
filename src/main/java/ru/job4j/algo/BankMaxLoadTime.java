package ru.job4j.algo;

import java.util.*;

public class BankMaxLoadTime {
    public static int[] findMaxLoadTime(List<Integer[]> visitTimes) {
        int maxLoadStartTime = 0;
        int maxLoadEndTime = 0;

        Deque<Integer> maxTimeInterval = new ArrayDeque<>();
        int max = 0;
        int current = 0;
        boolean endInterval = false;
        List<Event> events = getEventsSequence(visitTimes);
        for (Event event: events) {
            if (event.type.equals(EventType.ARRIVAL)) {
                current++;
            }
            if (event.type.equals(EventType.DEPARTURE)) {
                current--;
            }
            if (current > max) {
                max = current;
                maxTimeInterval.clear();
                endInterval = false;
            }
            if (!endInterval && current >= max) {
                maxTimeInterval.add(event.time);
            }
            if (!endInterval && max - current == 1) {
                maxTimeInterval.add(event.time);
                endInterval = true;
            }
        }
        maxLoadStartTime = maxTimeInterval.getFirst();
        maxLoadEndTime = maxTimeInterval.getLast();

        return new int[]{maxLoadStartTime, maxLoadEndTime};
    }

    static class Event implements Comparable<Event> {
        int time;
        EventType type;

        Event(int time, EventType type) {
            this.time = time;
            this.type = type;
        }

        @Override
        public int compareTo(Event other) {
            if (this.time == other.time) {
                return this.type == EventType.ARRIVAL ? 1 : -1;
            }
            return Integer.compare(this.time, other.time);
        }
    }

    enum EventType {
        ARRIVAL, DEPARTURE
    }

    private static List<Event> getEventsSequence(List<Integer[]> visitTimes) {
        List<Event> events = new ArrayList<>();

        visitTimes.forEach(s -> {
            Event arrival = new Event(s[0], EventType.ARRIVAL);
            Event departure = new Event(s[1], EventType.DEPARTURE);
            events.add(arrival);
            events.add(departure);
        });

        Collections.sort(events);
        return events;
    }
}
