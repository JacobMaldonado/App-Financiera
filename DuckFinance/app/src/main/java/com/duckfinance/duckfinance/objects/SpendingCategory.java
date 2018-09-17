package com.duckfinance.duckfinance.objects;

public class SpendingCategory {
    private String name;
    private int idImage;

    public SpendingCategory(String name, int idImage) {
        this.name = name;
        this.idImage = idImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }
}
