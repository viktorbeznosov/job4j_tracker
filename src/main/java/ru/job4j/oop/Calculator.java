package ru.job4j.oop;

public class Calculator {

    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public static int minus(int y) {
        return y - x;
    }

    public int multiply(int a) {
        return x * a;
    }

    public float divide(int y) {
        return (float) y / x;
    }

    public double add(double first, double second) {
        return first + second;
    }

    public double add(double first, double second, double third) {
        return add(
                first,
                add(second, third)
        );
    }

    public float sumAllOperation(int y) {
        return sum(y) + minus(y) + this.multiply(y) + this.divide(y);
    }

    public static void main(String[] args) {
        int result = Calculator.sum(10);
        System.out.println(result);
        Calculator calculator = new Calculator();
        int rsl = calculator.multiply(5);
        int difference = Calculator.minus(3);
        System.out.println(difference);
        System.out.println(rsl);
        float divisionn = calculator.divide(10);
        System.out.println(divisionn);
        float sumAll = calculator.sumAllOperation(10);
        System.out.println(sumAll);
    }

}
