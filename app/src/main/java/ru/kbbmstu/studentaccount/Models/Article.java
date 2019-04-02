package ru.kbbmstu.studentaccount.Models;

public final class Article {
    private int id;
    private String name;
    private String journal;
    private String biblioRecord;
    private String articlType;
    private boolean confirmed;

    public boolean isFile() {
        return file;
    }

    public void setFile(boolean file) {
        this.file = file;
    }

    private boolean file;


    public Article(int id, String name, String journal, String biblioRecord, String articlType, boolean confirmed, String fileName) {
        this.id = id;
        this.name = name;
        this.journal = journal;
        this.biblioRecord = biblioRecord;
        this.articlType = articlType;
        this.confirmed = confirmed;
        if (fileName.isEmpty())
            this.file = false;
        else
            this.file = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getBiblioRecord() {
        return biblioRecord;
    }

    public void setBiblioRecord(String biblioRecord) {
        this.biblioRecord = biblioRecord;
    }

    public String getArticlType() {
        return articlType;
    }

    public void setArticlType(String articlType) {
        this.articlType = articlType;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}


