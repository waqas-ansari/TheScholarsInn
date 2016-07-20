package com.arktech.waqasansari.thescholarsinn;

/**
 * Created by Linta Ansari on 7/16/2016.
 */
public class ClassTimeTable {
    private String className;
    private String testDays;
    private String timeTable;


    //Getters
    public String getClassName() {
        return className;
    }

    public String getTestDays() {
        return testDays;
    }

    public String getTimeTable() {
        return timeTable;
    }



    //Setters
    public void setClassName(String className) {
        this.className = className;
    }

    public void setTestDays(String testDays) {
        this.testDays = testDays;
    }

    public void setTimeTable(String timeTable) {
        this.timeTable = timeTable;
    }
}
