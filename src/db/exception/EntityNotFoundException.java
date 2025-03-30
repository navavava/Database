package db.exception;

public class EntityNotFoundException extends RuntimeException {

    EntityNotFoundException() {
        super("Cannot find entity");
    }

    EntityNotFoundException(String message) {
        super(message);
    }

    EntityNotFoundException(int id) {
        super("Cannot find entity with id = " + id);
    }

}
