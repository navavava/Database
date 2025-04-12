package db.todo.validator;
import db.Entity;
import db.Validator;
import db.exception.EntityNotFoundException;
import db.exception.InvalidEntityException;
import db.todo.entity.Step;
import static db.Database.entityExists;


public class StepValidator implements Validator {
    @Override
    public void validate(Entity entity) throws InvalidEntityException {
        if(!(entity instanceof Step))
            throw new IllegalArgumentException("This validator only takes 'Step' type entities as input.");
        else if(((Step) entity).title.isEmpty() || ((Step) entity).title.equals("null"))
            throw new InvalidEntityException("Step title must contain a non empty string");
        else if(!(entityExists(((Step) entity).taskRef)))
            throw new EntityNotFoundException("This task does not exist.");
    }
}
