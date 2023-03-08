package com.example.demo;

public class News {
    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public News( String title,String img) {
        this.img = img;
        this.title = title;
    }
}
