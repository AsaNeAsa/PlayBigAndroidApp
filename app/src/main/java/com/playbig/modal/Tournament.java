package com.playbig.modal;

/**
 * Created by User on 2/11/2016.
 */
public class  Tournament  {

    private String title, genre, year;

    public Tournament() {
    }

    public Tournament(String title) {
        this.title = title;

    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String name) {
        this.title = name;
    }
}
