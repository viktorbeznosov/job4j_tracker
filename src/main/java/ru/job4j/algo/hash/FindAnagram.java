package ru.job4j.algo.hash;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

public class FindAnagram {
    public static Set<Character> toSet(String str) {
        return str.chars()
                .mapToObj(c -> (char) c)
                .collect(
                        Collectors.toCollection(HashSet::new)
                );
    }

    private static List<Integer> findAnagrams(String str, String substr) {
        var result = new ArrayList<Integer>();
        var anagrams = toSet(substr);
        int windowSize = substr.length();
        for (int i = 0; i <= str.length() - windowSize; i++) {
            String window = str.substring(i, i + windowSize);
            if (anagrams.equals(toSet(window))) {
                result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> anagramIndices = findAnagrams(s, p);
        System.out.println(anagramIndices);
    }
}