package com.arktech.waqasansari.thescholarsinn.support_classes;

/**
 * Created by WaqasAhmed on 6/15/2016.
 */
public class ClassAnnouncement {
    private String title;
    private String message;
    private String date;
    private String notified = null;
    private String displayOnMain = null;

    public ClassAnnouncement() {
        //Necessary for firebase
    }

    //Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNotified(String notified) {
        this.notified = notified;
    }

    public void setDisplayOnMain(String displayOnMain) {
        this.displayOnMain = displayOnMain;
    }

    //Getters
    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public String getNotified() {
        return notified;
    }

    public String getDisplayOnMain() {
        return displayOnMain;
    }
}
