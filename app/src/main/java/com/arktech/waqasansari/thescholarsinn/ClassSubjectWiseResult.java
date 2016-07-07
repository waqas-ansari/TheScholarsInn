package com.arktech.waqasansari.thescholarsinn;

/**
 * Created by Linta Ansari on 7/2/2016.
 */
public class ClassSubjectWiseResult {
    private String subject;
    private int obtMarks=0;
    private int maxMarks=0;

    //Setters
    public void setSubject(String subject) {
        this.subject = subject;
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

    public int getObtMarks() {
        return obtMarks;
    }

    public int getMaxMarks() {
        return maxMarks;
    }
}
