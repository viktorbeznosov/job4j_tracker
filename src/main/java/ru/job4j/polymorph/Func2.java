package ru.job4j.polymorph;

public class Func2 implements Func1 {
    public double func(double x, double y) {
        return x * x - y + 5;
    }

    public void funcMessage() {
        System.out.println("Сообщение из Func2");
    }
}
