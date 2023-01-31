package ru.job4j.poly;

public class TransportUsage {
    public static void main(String[] args) {
        Vehicle jet = new Jet();
        Vehicle train = new Train();
        Vehicle autobus = new Autobus();

        Vehicle[] vehicles = new Vehicle[] {jet, train, autobus};
        for (Vehicle vehicle: vehicles) {
            vehicle.move();
        }
    }
}
