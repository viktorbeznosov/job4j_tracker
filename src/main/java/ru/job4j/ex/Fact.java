package ru.job4j.ex;

public class Fact {
    public int calc(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n should be more than zero.");
        }
        int rsl = 1;
        for (int index = 2; index <= n; index++) {
            rsl *= index;
        }
        return rsl;
    }

    public static void main(String[] args) {
        System.out.println(new Fact().calc(3));
    }
}
