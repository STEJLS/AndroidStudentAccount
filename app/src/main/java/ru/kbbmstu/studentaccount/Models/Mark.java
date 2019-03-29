package ru.kbbmstu.studentaccount.Models;

public final class Mark {
    private String subject;
    private int semester;
    private int rating;
    private String passType;
    private boolean repass;

    public Mark(String subject, int semester, int rating, String passType, boolean repass) {
        this.subject = subject;
        this.semester = semester;
        this.rating = rating;
        this.passType = passType;
        this.repass = repass;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getPassType() {
        return passType;
    }

    public void setPassType(String passType) {
        this.passType = passType;
    }

    public boolean isRepass() {
        return repass;
    }

    public void setRepass(boolean repass) {
        this.repass = repass;
    }
}
