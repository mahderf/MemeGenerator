package com.mahi.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Memes {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String topCaption;
    private String bottomCaption;
    private String thememe;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopCaption() {
        return topCaption;
    }

    public void setTopCaption(String topCaption) {
        this.topCaption = topCaption;
    }

    public String getBottomCaption() {
        return bottomCaption;
    }

    public void setBottomCaption(String bottomCaption) {
        this.bottomCaption = bottomCaption;
    }

    public String getThememe() {
        return thememe;
    }

    public void setThememe(String thememe) {
        this.thememe = thememe;
    }
}
