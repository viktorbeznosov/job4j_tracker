package ru.job4j.collection;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String[] splitedLeft = left.split(Pattern.quote("."));
        String[] splitedRight = right.split(Pattern.quote("."));
        int liftNum = Integer.parseInt(splitedLeft[0]);
        int rightNum = Integer.parseInt(splitedRight[0]);

        return Integer.compare(liftNum, rightNum);
    }
}