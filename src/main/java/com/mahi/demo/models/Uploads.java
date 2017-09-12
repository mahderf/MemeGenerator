package com.mahi.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Uploads {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String memestyle;
    private String images;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMemestyle() {
        return memestyle;
    }

    public void setMemestyle(String memestyle) {
        this.memestyle = memestyle;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}


