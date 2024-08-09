package ru.job4j.algo.sort;

import java.util.*;

public class IntervalMerger {
    public int[][] merge(int[][] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            Arrays.sort(intervals[i]);
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        int count = 0;
        List<List<Integer>> resultList = new ArrayList<>();
        resultList.add(Arrays.asList(intervals[0][0], intervals[0][1]));

        for (int i = 0; i < intervals.length; i++) {
            if (i > 0) {
                boolean intersect = intervals[i - 1][1] >= intervals[i][0];
                if (intersect) {
                    resultList.set(count, Arrays.asList(resultList.get(count).get(0), intervals[i][1]));
                } else {
                    count++;
                    resultList.add(Arrays.asList(intervals[i][0], intervals[i][1]));
                }
            }
        }

        int[][] result = new int[count + 1][2];
        for (int i = 0; i <= count; i++) {
            result[i][0] = resultList.get(i).get(0);
            result[i][1] = resultList.get(i).get(1);
        }

        return result;
    }

    public static void main(String[] args) {
        long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        IntervalMerger intervalMerger = new IntervalMerger();
        int[][] arr = new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] mergedArr = intervalMerger.merge(arr);

        for (int i = 0; i < mergedArr.length; i++) {
            System.out.println(mergedArr[i][0] + " " + mergedArr[i][1]);
        }
        long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long actualMemUsed = afterUsedMem - beforeUsedMem;
        System.out.println(actualMemUsed);
    }
}
