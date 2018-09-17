package com.duckfinance.duckfinance.objects;

public class Tip {

    private String title;
    private String description;
    private int imageID;

    public Tip(String title, String description, int imageID) {
        this.title = title;
        this.description = description;
        this.imageID = imageID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
