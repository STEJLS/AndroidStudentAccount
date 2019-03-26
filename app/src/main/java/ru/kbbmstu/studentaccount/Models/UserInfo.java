package ru.kbbmstu.studentaccount.Models;

import org.json.JSONException;
import org.json.JSONObject;

public final class UserInfo {
    private String number;
    private String fullName;
    private String group;
    private String field;
    private String fieldProfile;
    private String fieldCode;
    private String department;
    private String facultyName;

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

    public String getGroup() {
        return group;
    }

    public void setGroup(String team) {
        this.group = team;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFaculty() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public UserInfo(String number, String fullName, String group, String field, String fieldCode, String fieldProfile, String department, String facultyName) {
        this.number = number;
        this.fullName = fullName;
        this.group = group;
        this.field = field;
        this.fieldCode = fieldCode;
        this.fieldProfile = fieldProfile;
        this.department = department;
        this.facultyName = facultyName;
    }

    public UserInfo(JSONObject json) {
        try {
            number = json.getString("Number");
            fullName = json.getString("FullName");
            group = String.format("%s-%s", json.getString("Team"), json.getString("TeamNumber"));
            field = String.format("%s(%s)", json.getString("FieldName"), json.getString("FieldCode"));
            department = String.format("%s(%s)", json.getString("DepartmentName"), json.getString("DepartmentShortName"));
            facultyName = String.format("%s(%s)", json.getString("FacultyName"), json.getString("FacultyShortName"));
            fieldProfile = json.getString("FieldProfile");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
