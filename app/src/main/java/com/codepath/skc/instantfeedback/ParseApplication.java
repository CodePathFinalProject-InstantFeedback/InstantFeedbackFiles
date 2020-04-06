package com.codepath.skc.instantfeedback;

import android.app.Application;

import com.codepath.skc.instantfeedback.Models.Course;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        ParseObject.registerSubclass(Course.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("instantfeedback") // should correspond to APP_ID env variable
                .clientKey("CodepathMoveFastParse")  // set explicitly unless clientKey is explicitly configured on Parse server
                .server("https://instantfeedback.herokuapp.com/parse/").build());
        //Test to check if the parse dashboard is working, and we can control
        //the parse application from the database.
        ParseObject testObject = new ParseObject("U");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
    }



}
