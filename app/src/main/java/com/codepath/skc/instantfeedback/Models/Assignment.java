package com.codepath.skc.instantfeedback.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Assignment")

public class Assignment extends ParseObject {

    public static final String KEY_ASSIGNMENTNAME = "AssignmentName";
    public static final String KEY_COURSEPOINTER = "CoursePointer";
    public static final String KEY_ASSIGNMENTDESCRIPTION = "AssignmentDescription";
    public static final String KEY_TOTALRATING = "TotalRating";
    public static final String KEY_GETNUMBEROFRATINGS = "NumberOfRatings";

    public void setKeyAssignmentname(String assignmentName){
        put(KEY_ASSIGNMENTNAME,assignmentName);
    }


    public String getKeyAssignmentname() {
        return getString(KEY_ASSIGNMENTNAME);
    }

    public ParseObject getKeyCoursepointer() {
        return getParseObject(KEY_COURSEPOINTER);
    }

    public String getKeyAssignmentdescription() {
        return getString(KEY_ASSIGNMENTDESCRIPTION);
    }

    public void setKeyAssignmentdescription(String assignmentDescription){
        put(KEY_ASSIGNMENTNAME,assignmentDescription);
    }

    public float getKeyTotalrating() {

        return getNumber(KEY_TOTALRATING).floatValue();
    }

    public void setKeyAveragerating(float setRating){
        put(KEY_TOTALRATING,setRating);
    }

    public int getNumberOfRatings(){
        return getNumber(KEY_GETNUMBEROFRATINGS).intValue();
    }

    public void setNumberOfRatings(int count){
         put(KEY_GETNUMBEROFRATINGS,count);
    }

}
