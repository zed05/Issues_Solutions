package com.example.zed.issues_solutions;

public class News {
    private String img;
    private String title;
    private String summary;
    private String author;
    private String date;

    public News(String img, String title, String summary, String author, String date) {
        this.img = img;
        this.title = title;
        this.summary = summary;
        this.author = author;
        this.date = date;
    }

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
