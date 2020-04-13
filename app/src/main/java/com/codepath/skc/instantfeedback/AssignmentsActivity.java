package com.codepath.skc.instantfeedback;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.codepath.skc.instantfeedback.Models.Assignment;
import com.codepath.skc.instantfeedback.Models.Course;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class AssignmentsActivity extends AppCompatActivity {
    public static final String TAG="AssignmentActivity";
    Course course;
    RecyclerView rvAssignments;
    List<Assignment> assignments;
    AssignmentsAdapter assignmentsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);
        Intent i = getIntent();
        course=Parcels.unwrap(i.getParcelableExtra("course"));
        getSupportActionBar().setTitle(course.getKeyCoursename());
        rvAssignments=findViewById(R.id.rvAssignments);
        assignments=new ArrayList<>();
        assignmentsAdapter=new AssignmentsAdapter(this,assignments);
        rvAssignments.setAdapter(assignmentsAdapter);
        rvAssignments.setLayoutManager(new LinearLayoutManager(this));
        Log.i(TAG,"Inside Stream Fragment!");
        Assignment assignment1=new Assignment();
        assignment1.setAssignName("Assignment 1");
        assignment1.setDeadline("Jan 20");
        Assignment assignment2=new Assignment();
        assignment2.setAssignName("Assignment 2");
        assignment2.setDeadline("Feb 20");
        Assignment assignment3=new Assignment();
        assignment3.setAssignName("Assignment 3");
        assignment3.setDeadline("March 20");
        Assignment assignment4=new Assignment();
        assignment4.setAssignName("Assignment 4");
        assignment4.setDeadline("April 20");
        Assignment assignment5=new Assignment();
        assignment5.setAssignName("Assignment 5");
        assignment5.setDeadline("March 20");
        assignments.add(assignment1);
        assignments.add(assignment2);
        assignments.add(assignment3);
        assignments.add(assignment4);
        assignments.add(assignment5);
        assignmentsAdapter.notifyDataSetChanged();
        //queryPosts();
    }

    /*protected void queryPosts() {
        ParseQuery<Course> query= ParseQuery.getQuery(Assignment.class);
        //query.include(Course.KEY_USER);
        //query.addDescendingOrder(Post.CREATED_AT);
        query.findInBackground(new FindCallback<Course>() {
            @Override
            public void done(List<Course> courses, ParseException e) {
                if (e!=null)
                {
                    Log.e(TAG,"Not getting Posts!",e);
                }

                for(Course course:courses){
                    Log.i(TAG,"CourseName:"+course.getKeyCoursename()+",Description"+course.getKeyCoursedescription());
                }
                assignments.addAll(courses);
                courseAdapter.notifyDataSetChanged();
            }
        });
    }
    }

    private void sayHello() {
        Log.i(TAG,course.getKeyCoursename());
        assignments=course.getAssignments();
*/
}
