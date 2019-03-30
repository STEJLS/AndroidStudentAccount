package ru.kbbmstu.studentaccount.Models;

public final class Practice {
    private int semester;
    private String name;
    private String head;
    private String company;
    private String date;
    private int rating;

    public Practice(int semester, String name, String head, String company, String date, int rating) {
        this.semester = semester;
        this.name = name;
        this.head = head;
        this.company = company;
        this.date = date;
        this.rating = rating;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
