package com.codepath.skc.instantfeedback.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Course")

public class Course extends ParseObject {

    public static final String KEY_COURSENAME="CourseName";
    public static final String KEY_COURSEDESCRIPTION="CourseDescription";
    public static final String KEY_INSTRUCTOR="Insturctor";
    

    public void setCourseName(String courseName) {
        put( KEY_COURSENAME,courseName);
    }

    public void setCourseDescription(String courseDescription) {
        put( KEY_COURSENAME,courseDescription);
    }

    public void setInsturctor(String insturctor) {
        put( KEY_COURSENAME,insturctor);
    }

    public String getKeyCoursename() {
        return getString(KEY_COURSENAME);
    }

    public String getKeyCoursedescription() {
        return getString(KEY_COURSEDESCRIPTION);
    }

    public String getKeyInstructor() {
        return getString(KEY_INSTRUCTOR);
    }

}
