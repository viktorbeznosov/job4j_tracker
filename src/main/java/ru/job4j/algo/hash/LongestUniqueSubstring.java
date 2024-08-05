package ru.job4j.algo.hash;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LongestUniqueSubstring {
    public static Set<Character> toSet(String str) {
        return str.chars()
                .mapToObj(c -> (char) c)
                .collect(
                        Collectors.toCollection(HashSet::new)
                );
    }

    public static String longestUniqueSubstring(String str) {
        if (str.length() == 0) {
            return "";
        }
        Set<Character> uniqueElements = toSet(str);
        int setLength = uniqueElements.size();
        for (int i = setLength; i >= 1; i--) {
            for (int j = 0; j <= str.length() - i; j++) {
                String window = str.substring(j, j + i);
                if (window.length() == toSet(window).size()) {
                    return window;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println(longestUniqueSubstring("abcbcde"));
        long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long actualMemUsed = afterUsedMem - beforeUsedMem;
        System.out.println(actualMemUsed);
    }
}