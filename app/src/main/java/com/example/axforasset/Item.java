package com.example.axforasset;

import java.io.Serializable;

public class Item implements Serializable {
    private String title;
    private String description;
    private int imageResourceId;

    public Item(String title, String description, int imageResourceId) {
        this.title = title;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
