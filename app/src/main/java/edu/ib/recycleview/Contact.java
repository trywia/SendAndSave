package edu.ib.recycleview;

import java.io.Serializable;

public class Contact implements Serializable {
    private String date;
    private String message;

    public Contact(String date, String message) {
        this.date = date;
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }
}
