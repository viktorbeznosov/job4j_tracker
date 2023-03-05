package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int rsl = Integer.compare(left.length(), right.length());
        int minLength = left.length() > right.length() ? right.length() : left.length();
        for (int i = 0; i < minLength; i++) {
            rsl += Character.compare(left.charAt(i), right.charAt(i));
        }
        return rsl;
    }
}