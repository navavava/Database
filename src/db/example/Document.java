package db.example;
import db.Entity;
import db.Trackable;
import java.util.Date;

public class Document extends Entity implements Trackable {
    public static final int DOC_ENTITY_CODE = 15;
    public String content;
    private Date CreationDate;
    private Date LastModificationDate;

    public Document(String content) {
        this.content = content;
    }

    @Override
    public int getEntityCode() {
        return DOC_ENTITY_CODE;
    }

    @Override
    public void setCreationDate(Date date) {
        CreationDate = date;
    }

    @Override
    public Date getCreationDate() {
        return CreationDate;
    }

    @Override
    public void setLastModificationDate(Date date) {
        LastModificationDate = date;
    }

    @Override
    public Date getLastModificationDate() {
        return LastModificationDate;
    }
}
