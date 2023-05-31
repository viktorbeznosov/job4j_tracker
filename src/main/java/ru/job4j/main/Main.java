package ru.job4j.main;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int number1 = 123;
        int number2 = 456;
        System.out.println(number1 / 100 * 100 + number2 % 100);
        System.out.println(number2 / 100 * 100 + number1 % 100);
    }
}
