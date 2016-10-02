package com.arktech.waqasansari.thescholarsinn.support_classes;

/**
 * Created by Linta Ansari on 7/2/2016.
 */
public class ClassDetailedMarks {
    private String subject;
    private String testDate;
    private int obtMarks;
    private int maxMarks;


    //Setters
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public void setObtMarks(int obtMarks) {
        this.obtMarks = obtMarks;
    }

    public void setMaxMarks(int maxMarks) {
        this.maxMarks = maxMarks;
    }

    //Getters
    public String getSubject() {
        return subject;
    }

    public String getTestDate() {
        return testDate;
    }

    public int getObtMarks() {
        return obtMarks;
    }

    public int getMaxMarks() {
        return maxMarks;
    }
}
