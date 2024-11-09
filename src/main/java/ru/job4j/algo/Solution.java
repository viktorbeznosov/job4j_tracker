package ru.job4j.algo;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Solution {
    public static int[] sortedSquares(int[] nums) {
        int length = nums.length;
        int[] result = new int[length];
        int left = 0;
        int right = length - 1;
        int resultIndex = length - 1;
        while (left <= right) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                result[resultIndex] = nums[left] * nums[left];
                left++;
            } else {
                result[resultIndex] = nums[right] * nums[right];
                right--;
            }
            resultIndex--;
        }

        return result;
    }

    public static int maxArea(int[] height) {
        int maxHeight = 0;
        int maxHeightPointer = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > maxHeight) {
                maxHeight = height[i];
                maxHeightPointer = i;
            }
        }

        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            if (i != maxHeightPointer) {
                int food = Math.abs(maxHeightPointer - i);
                int h = height[i];
                int area = food * h;
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }

        return maxArea;
    }

    private static int getCharCount(char ch, String s) {
        int charCount = s.length() - s.replace(Character.toString(ch), "").length();
        return charCount;
    }

    public static List<Integer> partitionLabels(String s) {
        List<Integer> result = new ArrayList<>();
        int firstPointer = 0;
        int lastPointer = 0;

        while (lastPointer < s.length()) {
            char currentChar = s.charAt(firstPointer);

            if (getCharCount(currentChar, s) > 0) {
                lastPointer = s.lastIndexOf(Character.toString(currentChar));
                int count = firstPointer++;
                while (count < lastPointer) {
                    currentChar = s.charAt(count);
                    if (getCharCount(currentChar, s) > 0 && s.lastIndexOf(Character.toString(currentChar)) > lastPointer) {
                        lastPointer = s.lastIndexOf(Character.toString(currentChar));
                    }
                    count++;
                }
            }
            lastPointer++;
            result.add(lastPointer - firstPointer + 1);
            firstPointer = lastPointer;
        }

        return result;
    }

    public static int subarraySum(int[] nums, int k) {
        int firstPointer = 0;
        int secondPointer = 0;
        int count = 0;
        int sum = 0;
        while (firstPointer < nums.length && secondPointer < nums.length) {
            if (nums[firstPointer] == k) {
                count++;
                firstPointer++;
                secondPointer++;
            } else if (nums[firstPointer] > k) {
                firstPointer++;
                secondPointer++;
            } else {
                while (sum < k && secondPointer < nums.length) {
                    sum += nums[secondPointer];
                    if (sum >= k) {
                        if (sum == k) {
                            count++;
                        }
                        firstPointer = secondPointer;
                        sum = 0;
                        break;
                    }
                    secondPointer++;
                }
            }
        }
        return count;
    }

    public static boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {
            if (map1.get(s1.charAt(i)) != null) {
                map1.put(s1.charAt(i), map1.get(s1.charAt(i)) + 1);
            } else {
                map1.put(s1.charAt(i), 1);
            }
        }
        int leftPointer = 0;
        int rightPointer = s1.length() - 1;
        while (rightPointer < s2.length()) {
            for (int i = leftPointer; i <= rightPointer; i++) {
                if (map2.get(s2.charAt(i)) != null) {
                    map2.put(s2.charAt(i), map2.get(s2.charAt(i)) + 1);
                } else {
                    map2.put(s2.charAt(i), 1);
                }
            }
            if (map2.equals(map1)) {
                return true;
            }
            map2.clear();
            leftPointer++;
            rightPointer++;
        }

        return false;
    }

    public static int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == null) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }

        return map.keySet().stream().filter(s -> map.get(s) == 1).findFirst().get();
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.get(target - nums[i]) != null) {
                int[] result = {map.get(target - nums[i]), i};
                return result;
            } else {
                map.put(nums[i], i);
            }
        }

        return null;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        return null;
    }

    public static List<Character> toSortedList(String str) {
        List<Character> list = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.sort(list);
        return list;
    }

    public static boolean isAnagram(String s, String t) {
        return toSortedList(s).equals(toSortedList(t));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<List<Character>, List<String>> map = new HashMap<>();
        List<Character> keyList = new ArrayList<>();

        for (int i = 0; i < strs.length; i++) {
            keyList = strs[i].chars().mapToObj(e -> (char) e).collect(Collectors.toList());
            Collections.sort(keyList);
            if (map.get(keyList) != null) {
                map.get(keyList).add(strs[i]);
            } else {
                List<String> list = new ArrayList<>();
                list.add(strs[i]);
                map.put(keyList, list);
            }
        }

        return map.values().stream().collect(Collectors.toList());
    }

    public static double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        for (int i = 0; i <= nums.length - k; i++) {
            var window = Arrays.stream(Arrays.copyOfRange(nums, i, i + k)).asDoubleStream().boxed().collect(Collectors.toCollection(PriorityQueue<Double>::new)).toArray();
            double median = (k % 2 == 0)
                ? ((double) window[(int) k / 2 - 1] + (double) window[(int) k / 2]) / 2
                : (double) window[(int) k / 2];
            result[i] = median;
        }
        return result;
    }

    public static String longestCommonPrefix(String[] strs) {
        int minLength = strs[0].length();
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < minLength) {
                minLength = strs[i].length();
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < minLength; i++) {
            int finalI = i;
            Set<Character> set = Arrays.stream(strs).map(s -> s.charAt(finalI)).collect(Collectors.toSet());
            if (set.size() > 1) {
                break;
            }
            builder.append(set.stream().findFirst().get());
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"dog", "racecar", "car"};
        System.out.println(longestCommonPrefix(strs));
    }
}
