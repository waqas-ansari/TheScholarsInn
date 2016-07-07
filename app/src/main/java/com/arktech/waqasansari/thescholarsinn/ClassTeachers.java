package com.arktech.waqasansari.thescholarsinn;

/**
 * Created by Linta Ansari on 6/18/2016.
 */
public class ClassTeachers {
    private String teacherName;
    private String qualification;
    private String institute;
    private String subject;
    private String email;

    //getters
    public String getTeacherName() {
        return teacherName;
    }

    public String getQualification() {
        return qualification;
    }

    public String getInstitute() {
        return institute;
    }

    public String getSubject() {
        return subject;
    }

    public String getEmail() {
        return email;
    }


    //setters
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
