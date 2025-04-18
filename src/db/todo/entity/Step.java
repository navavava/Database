package db.todo.entity;

import db.Entity;

public class Step extends Entity {

    public static final int STEP_ENTITY_CODE = 2;
    public String title;
    public Status status;
    public int taskRef;

    public enum Status {
        NotStarted,
        Completed;
    }

    public Step(String title, int taskRef, Status status) {
        this.title = title;
        this.status = status;
        this.taskRef = taskRef;
        this.entityCode = STEP_ENTITY_CODE;
    }

    @Override
    public int getEntityCode() {
        return STEP_ENTITY_CODE;
    }

    @Override
    public Step clone() throws CloneNotSupportedException {
        return (Step) super.clone();
    }

}
