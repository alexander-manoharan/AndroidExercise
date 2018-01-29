package com.alxndr.androidexercise.model;

/**
 * Created by manoharana on 29/01/18.
 */

public class MvpModel {
    private String title;
    private String description;
    private String imageRef;

    public MvpModel (String title, String description, String imageRef) {
        this.title = title;
        this.description = description;
        this.imageRef = imageRef;
    }
}
