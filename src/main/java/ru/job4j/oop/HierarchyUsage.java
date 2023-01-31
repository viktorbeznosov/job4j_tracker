package ru.job4j.oop;

public class HierarchyUsage {
    public static void main(String[] args) {
        
        Car car = new Car();
        Transport tp = car;
        System.out.println(tp.getClass());
        Object obj = car;
        System.out.println(obj.getClass());
        Object ocar = new Car();
        Car carFromObject = (Car) ocar;

        Object bicycle = new Bicycle();
    }
}
