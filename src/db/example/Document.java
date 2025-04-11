package db.example;

import db.Entity;
import db.Trackable;

import java.util.Date;

public class Document extends Entity implements Trackable {
    public static final int DOC_ENTITY_CODE = 15;
    public String content;

    public Document(String content) {
        this.content = content;
    }

    @Override
    public int getEntityCode() {
        return DOC_ENTITY_CODE;
    }

    @Override
    public void setCreationDate(Date date) {

    }

    @Override
    public Date getCreationDate() {
        return null;
    }

    @Override
    public void setLastModificationDate(Date date) {

    }

    @Override
    public Date getLastModificationDate() {
        return null;
    }
}
