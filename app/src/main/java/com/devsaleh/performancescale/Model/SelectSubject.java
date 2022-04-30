package com.devsaleh.performancescale.Model;

public class SelectSubject {
    private String subjectName;
    private String subjectGroupNumber;

    public SelectSubject(String subjectName, String subjectGroupNumber) {
        this.subjectName = subjectName;
        this.subjectGroupNumber = subjectGroupNumber;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectGroupNumber() {
        return subjectGroupNumber;
    }

    public void setSubjectGroupNumber(String subjectGroupNumber) {
        this.subjectGroupNumber = subjectGroupNumber;
    }
}
