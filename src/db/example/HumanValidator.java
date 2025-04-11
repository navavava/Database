package db.example;
import db.*;
import db.exception.*;

public class HumanValidator implements Validator {
    @Override
    public void validate(Entity entity) throws InvalidEntityException {
        if(!(entity instanceof Human)){
            throw new IllegalArgumentException("This validator is for 'Human' class only.");
        }
        if(((Human) entity).age < 0){
            throw new InvalidEntityException("Age cannot be negative.");
        }
        if(((Human) entity).name.isEmpty() || ((Human) entity).name.equals("null")){
            throw new InvalidEntityException("Name must contain a non empty string.");
        }
    }
}
