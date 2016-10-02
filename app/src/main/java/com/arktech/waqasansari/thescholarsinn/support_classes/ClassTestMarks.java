package com.arktech.waqasansari.thescholarsinn.support_classes;

/**
 * Created by WaqasAhmed on 6/15/2016.
 */
public class ClassTestMarks {
    private String studentName;
    private String fatherName;
    private String subject;
    private String className;
    private String testDate;
    private int marksMax;
    private int marksObtained;

    public static String subjects[] = {
            "Physics", "Mathematics", "Chemistry", "Computer",
            "Biology", "Zoology", "Botany",
            "Pakistan Studies", "Sindhi", "Islamiat", "Urdu", "English"
    };

    public static int PHYSICS=0, MATH=1, CHEM=2, COMP=3, BIO=4, ZOO=5, BOT=6, PST=7, SINDHI=8, ISL=9, URDU=10, ENG=11;

    //Getters
    public String getClassName() {
        return className;
    }

    public String getFatherName() {
        return fatherName;
    }

    public int getMarksMax() {
        return marksMax;
    }

    public int getMarksObtained() {
        return marksObtained;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getSubject() {
        return subject;
    }

    public String getTestDate() {
        return testDate;
    }

    //Setters
    public void setClassName(String className) {
        this.className = className;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setMarksMax(int marksMax) {
        this.marksMax = marksMax;
    }

    public void setMarksObtained(int marksObtained) {
        this.marksObtained = marksObtained;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }
}
