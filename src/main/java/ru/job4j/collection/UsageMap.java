package ru.job4j.collection;

import java.util.HashMap;
import java.util.Map;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("parsentev@yandex.ru", "Petr Arsentev");
        map.put("parsentev@yandex.ru", "Petr Arsentev");
        map.put("viktorbeznosov@mail.ru", "Viktor Beznosov");
        map.put("viktorbeznosov@mail.ru", "Viktor Georgievich Beznosov");
        map.put("someone@mail.ru", "Some Body");
        for (String key: map.keySet()) {
            System.out.println(key + " - " + map.get(key));
        }
    }
}
