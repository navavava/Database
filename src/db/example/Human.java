package db.example;

import db.Entity;

public class Human extends Entity {
    public String name;
    public int age;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }
}