package ru.job4j.poly;

public class Bus implements Transport {
    private int passengersCount;

    public static final int FUEL_PRICE = 50;

    @Override
    public void move() {
        System.out.println("The bus is moving");
    }

    @Override
    public void setPassengersCount(int passengersCount) {
        this.passengersCount = passengersCount;
    }

    @Override
    public int refuel(int fuel) {
        return fuel * FUEL_PRICE;
    }
}
