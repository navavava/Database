package db;

import db.exception.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    static private ArrayList<Entity> entities = new ArrayList<>();
    static public int nextId = 1;
    private static HashMap<Integer, Validator> validators = new HashMap<>();


    public static void add(Entity e) {
        e.id = nextId;
        nextId++;
        try {
            entities.add((Entity) e.clone());
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException("This object cannot be cloned.");
        }

    }

    public static Entity get(int id) {
        for (Entity e : entities) {
            if (e.id == id) {
                try {
                    return (Entity) e.clone();
                } catch (CloneNotSupportedException ex) {
                    throw new RuntimeException("This object cannot be cloned.");
                }
            }
        }
        throw new EntityNotFoundException(id);
    }

    public static void delete(int id) {
        boolean exists = false;
        for (Entity e : entities) {
            if (e.id == id) entities.remove(e);
            exists = true;
            break;
        }
        if (!exists)
            throw new EntityNotFoundException(id);
    }

    public static void update(Entity e) {
        boolean exists = false;
        for (Entity entity : entities) {
            if (e.id == entity.id) {
                try {
                    entities.remove(entity);
                    entities.add((Entity) e.clone());
                } catch (CloneNotSupportedException ex) {
                    throw new RuntimeException("This object cannot be cloned.");
                }
                exists = true;
                break;
            }
        }
        if (!exists)
            throw new EntityNotFoundException();
    }

    public static void registerValidator(int entityCode, Validator validator) {
        for (Integer key : validators.keySet()) {
            if (entityCode == key)
                throw new IllegalArgumentException("Validator with this code already exists.");
        }
        validators.put(entityCode,validator);
    }
}
