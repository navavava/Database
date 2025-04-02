package db;

import db.exception.*;

import java.util.ArrayList;

public class Database {
    static private ArrayList<Entity> entities = new ArrayList<>();
    static public int nextId = 1;

    public static void add(Entity e) {
        e.id = nextId;
        nextId++;
        entities.add(e.copy());
    }

    public static Entity get(int id) {
        for (Entity e : entities) {
            if (e.id == id) return e.copy();
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
                entities.remove(entity);
                entities.add(e.copy());
                exists = true;
                break;
            }
        }
        if (!exists)
            throw new EntityNotFoundException();
    }
}
