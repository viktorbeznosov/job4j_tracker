package ru.job4j.pojo;

public class Book {
    private String title;
    private int pagesCount;

    public Book(String title, int pagesCount) {
        this.title = title;
        this.pagesCount = pagesCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }
}
