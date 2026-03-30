package edu.uncc.assignment03;

import android.content.Intent;

public class Person {
    private String educationLevel;
    private String maritalStatus ;

    public Person() {
        // Default constructor
    }

    // Getters and setters for educationLevel and maritalStatus
    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;

    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
}
