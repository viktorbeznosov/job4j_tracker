package ru.job4j.pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFirstName("Виктор");
        student.setMiddleName("Георгиевич");
        student.setLastName("Безносов");
        student.setGroup(111);
        student.setReceptionDate(new Date());

        System.out.println(student.getLastName() + " " + student.getFirstName() + " " + student.getMiddleName()
        + ". Группа номер " + student.getGroup() + ". дата поступления " + student.getReceptionDate());
    }
}
