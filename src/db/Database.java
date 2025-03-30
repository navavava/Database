package db;

import db.exception.EntityNotFoundException;

import java.util.ArrayList;

public class Database {
    static private ArrayList<Entity> entities = new ArrayList<>();
    static public int nextId = 1;

    static void add(Entity e) {
        entities.add(e);
        e.id = nextId;
        nextId++;
    }

    static Entity get(int id) {
        for (Entity e : entities) {
            if (e.id == id) return e;
        }
        throw new EntityNotFoundException("Entity not found!");
    }

    static void delete(int id) {
        for (Entity e : entities) {
            if (e.id == id) entities.remove(e);
        }
        throw new EntityNotFoundException("Entity not found!");
    }

    static void update(Entity e) {
        for (Entity entity : entities) {
            if (e.id == entity.id) {
                entities.remove(entity);
                entities.add(e);
            }
        }
        throw new EntityNotFoundException("Entity not found!");
    }
}
