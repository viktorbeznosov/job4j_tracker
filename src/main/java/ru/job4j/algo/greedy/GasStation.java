package ru.job4j.algo.greedy;

class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int maxDifference = 0;
        int maxDifferenceIndex = 0;
        for (int i = 0; i < gas.length; i++) {
            if (gas[i] - cost[i] > maxDifference) {
                maxDifference = gas[i] - cost[i];
                maxDifferenceIndex = i;
            }
        }

        int tank = 0;
        int count = 0;
        int index = maxDifferenceIndex;
        int leftIndex = maxDifferenceIndex == 0 ? gas.length - 1 : maxDifferenceIndex - 1;
        int rightIndex = maxDifferenceIndex == gas.length - 1 ? 0 : maxDifferenceIndex + 1;
        int leftDifference = gas[leftIndex] - cost[leftIndex];
        int rightDifference = gas[rightIndex] - cost[rightIndex];
        boolean direction = rightDifference > leftDifference;

        while (count < gas.length) {
            tank += gas[index] - cost[index];
            if (index >= gas.length - 1 && direction) {
                index = 0;
            } else if (index <= 0) {
                index = gas.length;
            }
            count++;
            index = direction ? index + 1 : index - 1;
        }

        return tank >= 0 ? maxDifferenceIndex : -1;
    }
}