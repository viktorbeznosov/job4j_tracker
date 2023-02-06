package ru.job4j.strategy;

public abstract class ShapeOperator {
    public void showInfo() {
        Shape shape = createShape();
        System.out.println(shape.draw());
        System.out.println("Площадь фигуры равна: " + shape.square());
    }

    public abstract Shape createShape();
}
