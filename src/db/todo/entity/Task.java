package db.todo.entity;
import db.Entity;
import db.Trackable;
import java.util.Date;

public class Task extends Entity implements Trackable {
    public static final int TASK_ENTITY_CODE = 1;
    Date CreationDate;
    Date LastModificationDate;
    String title;
    String description;
    Date dueDate;
    Status status;

    public enum Status {
        NotStarted,
        InProgress,
        Completed;
    }

    public Task(String title, String description, Date dueDate, Status status){
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }

    @Override
    public int getEntityCode() {
        return TASK_ENTITY_CODE;
    }

    @Override
    public void setCreationDate(Date date) {
        this.CreationDate = date;
    }

    @Override
    public Date getCreationDate() {
        return CreationDate;
    }

    @Override
    public void setLastModificationDate(Date date) {
        this.LastModificationDate = date;
    }

    @Override
    public Date getLastModificationDate() {
        return LastModificationDate;
    }

    @Override
    public Task clone() throws CloneNotSupportedException {
        Task task = (Task)super.clone();
        task.CreationDate = new Date(this.CreationDate.getTime());
        task.LastModificationDate = new Date(this.LastModificationDate.getTime());
        return task;
    }

}
