package ru.job4j.poly;

public class Goose extends DomesticAnimal implements Animal {
    @Override
    public void sound() {
        System.out.println(getClass().getSimpleName() + " произносит звук: Га-га");
    }
}
