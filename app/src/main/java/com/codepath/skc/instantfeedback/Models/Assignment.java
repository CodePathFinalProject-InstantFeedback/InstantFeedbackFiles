package com.codepath.skc.instantfeedback.Models;

public class Assignment {
    public static final String KEY_ASSIGNMENTNAME="ASSIGNMENTNAME";
    private  String assignName;
    private String deadline;

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getAssignName() {
        return assignName;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }
}
