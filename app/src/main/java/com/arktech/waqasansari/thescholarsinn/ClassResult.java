package com.arktech.waqasansari.thescholarsinn;

/**
 * Created by Linta Ansari on 6/25/2016.
 */
public class ClassResult {
    private String ID;
    private String name;
    private String fatherName;
    private String _class;
    private int gainedMarks;
    private int totalMarks;
    private float percentage;


    //Setters
    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public void setGainedMarks(int gainedMarks) {
        this.gainedMarks = gainedMarks;
    }

    public void setTotalMarks(int totalMarls) {
        this.totalMarks = totalMarls;
    }

    public void setPercentage() {
        this.percentage = (float) (gainedMarks/totalMarks) * 100;
    }

    //Getters
    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String get_class() {
        return _class;
    }

    public int getGainedMarks() {
        return gainedMarks;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public float getPercentage() {
        return percentage;
    }

}
