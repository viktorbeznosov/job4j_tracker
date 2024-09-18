package ru.job4j.algo.recursion;

public class Recursion {
    public int loop(int summary, int index) {
        for (int i = index; i > 0; i--) {
            summary += i;
        }
        return summary;
    }

    public int sum(int summary, int index) {
        if (index > 0) {
            summary += index;
            index--;
            System.out.println(" ".repeat(index) + "before " + index);
            summary = sum(summary, index);
            System.out.println(" ".repeat(index) + "after " + index);
        }
        return summary;
    }

    public long factorialLoop(int f) {
        long result = 1L;
        if (f > 0) {
            for (int i = 1; i <= f; i++) {
                result = result * i;
            }
        }
        return result;
    }

    public long factorialRecursion(long index) {
        if (index == 0) {
            return 1;
        }
        long result = index;
        if (index > 1) {
            result *= factorialRecursion(index - 1);
        }
        return result;
    }

    public long fibonacciLoop(int n) {
        long result = 0L;
        if (n == 1) {
            result = 1L;
        } else if (n > 1) {
            long f1 = 1L;
            long f2 = 1L;
            for (int i = 0; i < (n - 2); i++) {
                result = f2 + f1;
                f1 = f2;
                f2 = result;
            }
        }
        return result;
    }

    public long fibonacciRecursion(int n) {
        long result;
        if (n == 0) {
            result = 0;
        } else if (n == 1) {
            result = 1;
        } else  {
            result = fibonacciRecursion(n - 1) + fibonacciRecursion(n - 2);
        }
        return result;
    }

    public static void main(String[] args) {
        Recursion recursion = new Recursion();
        System.out.println(recursion.fibonacciRecursion(3));
    }
}
