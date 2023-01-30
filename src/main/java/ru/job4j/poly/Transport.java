package ru.job4j.poly;

public interface Transport {
    void move();

    void setPassengersCount(int passengersCount);

    int refuel(int fuel);
}
