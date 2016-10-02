package com.arktech.waqasansari.thescholarsinn.support_classes;

/**
 * Created by Linta Ansari on 7/3/2016.
 */
public class ClassStudentAttendance {
    private char attendance;
    private String className;
    private String studentName;
    private String fatherName;
    private String date;


    //Getters
    public char getAttendance() {
        return attendance;
    }

    public String getClassName() {
        return className;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getDate() {
        return date;
    }



    //Setters
    public void setAttendance(char attendance) {
        this.attendance = attendance;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
