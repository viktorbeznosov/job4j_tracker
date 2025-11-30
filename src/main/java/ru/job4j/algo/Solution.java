package ru.job4j.algo;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {

        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ArrayList<Integer> list = new ArrayList<>();
        ListNode list1Current = list1;
        ListNode list2Current = list2;
        while (list1Current != null || list2Current != null) {
            if (list2Current == null || list1Current != null && list1Current.val <= list2Current.val) {
                list.add(list1Current.val);
                list1Current = list1Current.next;
            }
            if (list1Current == null || list2Current != null && list2Current.val < list1Current.val) {
                list.add(list2Current.val);
                list2Current = list2Current.next;
            }
        }

        ListNode next = null;
        ListNode current = null;
        for (int i = list.size() - 1; i >= 0; i--) {
            current = new ListNode(list.get(i), next);
            next = current;
        }

        return current;
    }

    public static int[] removeDuplicates(int[] nums) {
        int[] result = new int[nums.length];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        AtomicInteger count = new AtomicInteger(0);
        set.forEach(s -> {
            result[count.getAndIncrement()] = s;
        });

        return result;
    }

    public static int[] removeElement(int[] nums, int val) {
        List<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                tmp.add(nums[i]);
            }
        }
        int[] result = new int[nums.length];
        int count = 0;
        for (int item: tmp) {
            result[count] = item;
            count++;
        }

        return result;
    }

    public static int strStr(String haystack, String needle) {
        int index = -1;
        if (needle.length() > haystack.length()) {
            return index;
        }
        int i = 0;
        int j = 0;

        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                index = i;
                while (j < needle.length() && (i + j) < haystack.length() && needle.charAt(j) == haystack.charAt(i + j)) {
                    j++;
                }
                if (j == needle.length()) {
                    return index;
                } else {
                    j = 0;
                    index = -1;
                }
            }

            i++;
        }

        return index;
    }

    public static int searchInsert(int[] nums, int target) {
        int count = 0;
        while (count < nums.length && target > nums[count]) {
            count++;
        }

        return count;
    }

    public static int lengthOfLastWord(String s) {
        s = s.replaceAll("\s+", " ").trim();
        List<String> list = Arrays.stream(s.split(" ")).toList();

        return list.get(list.size() - 1).length();
    }

    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }

        int[] result = new int[digits.length + 1];
        result[0] = 1;
        for (int i = 0; i < digits.length; i++) {
            result[i + 1] = digits[i];
        }

        return result;
    }

    public static String addBinary(String a, String b) {
        int maxLength = Math.max(a.length(), b.length());
        if (a.length() != b.length()) {
            int difference = Math.abs(a.length() - b.length());
            String prefix = "0".repeat(difference);
            if (a.length() > b.length()) {
                b = prefix + b;
            } else {
                a = prefix + a;
            }
        }
        StringBuilder builder = new StringBuilder();
        char result = '0';
        int carry = 0;
        for (int i = maxLength - 1; i >= 0; i--) {
            if (a.charAt(i) == '1' && b.charAt(i) == '1') {
                result = carry == 0 ? '0' : '1';
                carry = 1;
            } else if (a.charAt(i) == '1' && b.charAt(i) == '0' || a.charAt(i) == '0' && b.charAt(i) == '1') {
                result = carry == 0 ? '1' : '0';
            } else {
                result = carry == 0 ? '0' : '1';
                carry = 0;
            }
            builder.append(result);
        }
        if (carry == 1) {
            builder.append('1');
        }

        return builder.reverse().toString();
    }

    public static int mySqrt(int x) {
        if (x == 0) {
            return x;
        }
        var max = 2.147483648E9;
        var current = max / 2;

        while (!(current * current <= x) && !((current + 1) * (current + 1) < x)) {
            if (current * current > x) {
                max = current;
                current = (int) Math.ceil((float) current / 2);
            } else {
                current = (int) Math.ceil(((float) current + max) / 2);
            }
        }

        while ((current + 1) * (current + 1) <= x) {
            if (x - (current + 1) * (current + 1) < (x - current * current)) {
                current += 1;
            }
        }

        return (int) current;
    }

    public static int climbStairs(int n) {
        int[] fibbonaci = new int[]{0, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141, 267914296, 433494437, 701408733, 1134903170, 1836311903
        };

        return fibbonaci[n];
    }

    public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int[] result = new int[m + n];
        int i = 0;
        int j = 0;
        int count = 0;

        if (m == 0) {
            for (int k = 0; k < nums2.length; k++) {
                nums1[k] = nums2[k];
            }
        } else if (m > 0 && n > 0) {
            if (nums1.length > 0 && nums2.length > 0) {
                while (i < m || j < n) {
                    if (j == nums2.length || i < m && nums1[i] <= nums2[j]) {
                        result[count] = nums1[i];
                        i++;
                    } else if (i == nums1.length || j < n) {
                        result[count] = nums2[j];
                        j++;
                    }
                    count++;
                }
            }

            for (int k = 0; k <  result.length; k++) {
                nums1[k] = result[k];
            }
        }

        return nums1;
    }

    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;

      TreeNode() {

      }

      TreeNode(int val) {
          this.val = val;
      }

      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        return inorderTraversal(root, result);
    }

    public static List<Integer> inorderTraversal(TreeNode localRoot, List<Integer> list) {
        if (localRoot != null) {
            inorderTraversal(localRoot.left, list);
            list.add(localRoot.val);
            inorderTraversal(localRoot.right, list);
        }
        return list;
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        boolean result = true;
        return isSameTree(p, q, result);
    }

    public static boolean isSameTree(TreeNode p, TreeNode q, boolean result) {
        if (p == null && q != null || p != null && q == null) {
            return false;
        }
        if (p != null && q != null) {
            result = isSameTree(p.left, q.left, result);
            if (p.val != q.val) {
                return false;
            }
            result = isSameTree(p.right, q.right, result);
        }
        return result;
    }

    public static boolean isSymmetric(TreeNode root) {
        return isSameTree(root, getMirrorTree(root));
    }

    public static TreeNode getMirrorTree(TreeNode root) {
        TreeNode current = new TreeNode(root.val);
        TreeNode temp = root.left;
        current.left = root.right;
        current.right = temp;
        if (current.left != null) {
            current.left = getMirrorTree(current.left);
        }
        if (current.right != null) {
            current.right = getMirrorTree(current.right);
        }
        return current;
    }

    public static int maxDepth(TreeNode root) {
        int depth = 1;
        if (root == null) {
            return 0;
        }
        return maxDepth(root, depth);
    }

    public static int maxDepth(TreeNode root, int depth) {
        if (root == null) {
            return depth - 1;
        }

        return Math.max(maxDepth(root.left, depth + 1), maxDepth(root.right, depth + 1));
    }

    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        if (root.left == null) {
            return 1 + minDepth(root.right);
        }

        if (root.right == null) {
            return 1 + minDepth(root.left);
        }

        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                return root.val - targetSum == 0;
            }
            boolean result = hasPathSum(root.left, targetSum - root.val);
            return (result)
                ? hasPathSum(root.left, targetSum - root.val)
                : hasPathSum(root.right, targetSum - root.val);
        }

        return false;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(
                5,
                new TreeNode(
                        4,
                        new TreeNode(
                                11,
                                new TreeNode(7),
                                new TreeNode(2)
                        ),
                        null),
                new TreeNode(
                        8,
                        new TreeNode(13),
                        new TreeNode(
                                4,
                                null,
                                new TreeNode(1)
                        )
                )
        );

//        TreeNode treeNode = new TreeNode(1, new TreeNode(2), new TreeNode(3));

        System.out.println(hasPathSum(treeNode, 26));
    }
}
