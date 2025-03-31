package db;

abstract public class Entity {
    public int id;

    public abstract Entity copy();
}
