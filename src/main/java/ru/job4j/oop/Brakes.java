package ru.job4j.oop;

public class Brakes {

    public void brake() {
        System.out.println("Торможение");
    }

    public static void main(String[] args) {
        Brakes brakes = new Brakes();
        brakes.brake();
    }

}
