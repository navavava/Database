package db;

abstract public class Entity implements Cloneable{
    public int id;
    public int entityCode;

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public abstract int getEntityCode();
}
