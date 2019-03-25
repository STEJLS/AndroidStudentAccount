package ru.kbbmstu.studentaccount.Models;

public final class UserInfo {
    public String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(String teamNumber) {
        this.teamNumber = teamNumber;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    public String getFieldProfile() {
        return fieldProfile;
    }

    public void setFieldProfile(String fieldProfile) {
        this.fieldProfile = fieldProfile;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getFacultyShortName() {
        return facultyShortName;
    }

    public void setFacultyShortName(String facultyShortName) {
        this.facultyShortName = facultyShortName;
    }

    public String fullName;
    public String team;
    public String teamNumber;
    public String fieldName;
    public String fieldCode;
    public String fieldProfile;
    public String departmentName;
    public String facultyName;
    public String facultyShortName;

    public UserInfo(String number, String fullName, String team, String teamNumber, String fieldName, String fieldCode, String fieldProfile, String departmentName, String facultyName, String facultyShortName) {
        this.number = number;
        this.fullName = fullName;
        this.team = team;
        this.teamNumber = teamNumber;
        this.fieldName = fieldName;
        this.fieldCode = fieldCode;
        this.fieldProfile = fieldProfile;
        this.departmentName = departmentName;
        this.facultyName = facultyName;
        this.facultyShortName = facultyShortName;
    }
}
