package ru.job4j.oop;

public class Car {
    private String brand;
    private String model;

    private static String carManual = "Инструкция к автомобилю";

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public void startEngine() {
        System.out.println("Двигатель запущен");
    }

    public static class Manual {

        public static String getManual() {
            Car car = new Car("Марка", "Модель");
            return "Модель: " + car.model + ", Инструкция: " + carManual;
        }

    }

    public class Transmission {

        public void accelerate() {
            System.out.println("Ускорение");
        }

    }

    public static TripComputer getTripComputer() {
        Car car = new Car("Марка", "Модель");
        Car.TripComputer tripComputer = car.new TripComputer();

        return tripComputer;
    }

    public class TripComputer {

        public String tripData = "Бортовой компьютер";

        private String model = "Модель TripComputer";

        public void getInfo() {
            System.out.println("Модель TripComputer: " + this.model);
            System.out.println("Модель Car: " + Car.this.model);
        }

    }

    public class Brakes {

        public void brake() {
            System.out.println("Торможение");
        }

    }
}
