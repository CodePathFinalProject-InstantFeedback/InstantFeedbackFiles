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
    public static final String KEY_GETANGERVAL = "Anger";
    public static final String KEY_GETJOYVAL = "Joy";
    public static final String KEY_GETSADNESSVAL = "Sadness";
    public static final String KEY_GETFEARVAL = "Fear";
    public static final String KEY_INDEX = "indexNumber";

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
        put(KEY_ASSIGNMENTDESCRIPTION,assignmentDescription);
    }

    public void setKeyCoursePointer(Course coursePointer)
    {
        put(KEY_COURSEPOINTER,coursePointer);
    }

    public float getKeyTotalrating() {

        return getNumber(KEY_TOTALRATING).floatValue();
    }

    public void setKeyTotalrating(float setRating){
        put(KEY_TOTALRATING,setRating);
    }

    public int getNumberOfRatings(){
        return getNumber(KEY_GETNUMBEROFRATINGS).intValue();
    }

    public void setNumberOfRatings(int count){
         put(KEY_GETNUMBEROFRATINGS,count);
    }


    public float getKeyGetangerval() {
        return getNumber(KEY_GETANGERVAL).floatValue();
    }

    public float  getKeyGetjoyval() {
        return getNumber(KEY_GETJOYVAL).floatValue();
    }

    public float getKeyGetsadnessval() {
        return getNumber(KEY_GETSADNESSVAL).floatValue();
    }

    public float getKeyGetfearval() {
        return getNumber(KEY_GETFEARVAL).floatValue();
    }

    public void setKeyangerval(float angerVal){
        put(KEY_GETANGERVAL,angerVal);
    }

    public void setKeyjoyval(float joyVal){
        put(KEY_GETJOYVAL,joyVal);
    }

    public void setKeysadnessval(float keysadnessVal){
        put(KEY_GETSADNESSVAL,keysadnessVal);
    }

    public void setKeyfearval(float fearval){
        put(KEY_GETFEARVAL,fearval);
    }

    public void setKeyIndex(int index) {
        put(KEY_INDEX,index);

    }

}
