package ru.kbbmstu.studentaccount.Models;

public final class Article {
    private int id;
    private String name;
    private String journal;
    private String biblioRecord;
    private String articlType;
    private String fileName;
    private boolean confirmed;


    public Article(int id, String name, String journal, String biblioRecord, String articlType, boolean confirmed, String fileName) {
        this.id = id;
        this.name = name;
        this.journal = journal;
        this.biblioRecord = biblioRecord;
        this.articlType = articlType;
        this.confirmed = confirmed;
        this.fileName = fileName;
    }

    public Article(String name, String journal, String biblioRecord, String articlType, String fileName) {
        this.name = name;
        this.journal = journal;
        this.biblioRecord = biblioRecord;
        this.articlType = articlType;
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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


