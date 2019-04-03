package ru.kbbmstu.studentaccount.Models;

public final class FosAndRpd {
    private String name;
    private int fosID;
    private int rpdID;

    public FosAndRpd(String name, int fosID, int rpdID) {
        this.name = name;
        this.fosID = fosID;
        this.rpdID = rpdID;
    }

    public String getName() {
        return name;
    }

    public int getFosID() {
        return fosID;
    }

    public int getRpdID() {
        return rpdID;
    }
}
