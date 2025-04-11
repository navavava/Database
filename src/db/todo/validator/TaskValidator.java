package db.todo.validator;

import db.Entity;
import db.Validator;
import db.exception.InvalidEntityException;
import db.todo.entity.Task;

public class TaskValidator implements Validator {

    @Override
    public void validate(Entity entity) throws InvalidEntityException {
        if(!(entity instanceof Task))
            throw new IllegalArgumentException("This validator only takes 'Task' type entities as input.");
        else if(((Task) entity).title.isEmpty() || ((Task) entity).title.equals("null"))
            throw new InvalidEntityException("Task title must contain a non empty string");
    }
}
