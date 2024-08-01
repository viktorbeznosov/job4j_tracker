package ru.job4j.algo;

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

    public static void main(String[] args) {
        int[] sortedArray = {-4, -2, 0, 2, 3, 5};
        int[] result = Solution.sortedSquares(sortedArray);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}
