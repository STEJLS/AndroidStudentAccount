package ru.kbbmstu.studentaccount.Models;

public final class CourseWork {
    private int id;
    private String author;
    private String head;
    private String subject;
    private String team;
    private String theme;
    private Boolean isValidTheme;
    private Boolean confirmed;
    private int rating;
    private int semester;

    public CourseWork(int id, String author, String head, String subject, String team, String theme, Boolean isValidTheme, Boolean confirmed, int rating, int semester) {
        this.id = id;
        this.author = author;
        this.head = head;
        this.subject = subject;
        this.team = team;
        this.theme = theme;
        this.isValidTheme = isValidTheme;
        this.confirmed = confirmed;
        this.rating = rating;
        this.semester = semester;
    }


    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getHead() {
        return head;
    }

    public String getSubject() {
        return subject;
    }

    public String getTeam() {
        return team;
    }

    public String getTheme() {
        return theme;
    }

    public Boolean isValidTheme() {
        return isValidTheme;
    }

    public Boolean isConfirmed() {
        return confirmed;
    }

    public int getRating() {
        return rating;
    }

    public int getSemester() {
        return semester;
    }
}
