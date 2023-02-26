package ru.job4j.queue;

import java.util.LinkedList;
import java.util.Queue;

public class AppleStore {
    private final Queue<Customer> queue;

    private final int count;

    public AppleStore(Queue<Customer> queue, int count) {
        this.queue = queue;
        this.count = count;
    }

    public String getLastHappyCustomer() {
        Queue<Customer> customers = new LinkedList<>();
        customers.addAll(queue);
        for (int i = 0; i < count - 1; i++) {
            customers.poll();
        }

        return customers.peek().name();
    }

    public String getFirstUpsetCustomer() {
        Queue<Customer> customers = new LinkedList<>();
        customers.addAll(queue);
        for (int i = 0; i < count; i++) {
            customers.poll();
        }

        return customers.peek().name();
    }
}
