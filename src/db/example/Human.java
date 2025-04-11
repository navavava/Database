package db.example;
import db.Entity;

public class Human extends Entity {
    public static final int HUMAN_ENTITY_CODE = 14;
    public String name;
    public int age;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int getEntityCode() {
        return HUMAN_ENTITY_CODE;
    }

    @Override
    public Human clone() throws CloneNotSupportedException {
        return (Human)super.clone();
    }
}