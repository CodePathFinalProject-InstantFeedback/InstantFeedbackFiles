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
    public static final String TAG = "AssignmentActivity";
    public String objectId;
    Course course;
    RecyclerView rvAssignments;
    List<Assignment> allAssignments;
    AssignmentsAdapter assignmentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);
        Intent i = getIntent();
        course = Parcels.unwrap(i.getParcelableExtra("course"));
        getSupportActionBar().setTitle(course.getKeyCoursename());
        rvAssignments = findViewById(R.id.rvAssignments);
        allAssignments = new ArrayList<>();
        assignmentsAdapter = new AssignmentsAdapter(this, allAssignments);
        rvAssignments.setAdapter(assignmentsAdapter);
        rvAssignments.setLayoutManager(new LinearLayoutManager(this));
        Log.i(TAG, "Inside Assignment Fragment!");
        queryPosts();
        //queryPosts();
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Log.i(TAG,"Back here! Update the Query!");
        finish();
    }

    protected void queryPosts() {
        ParseQuery<Assignment> query = ParseQuery.getQuery(Assignment.class);
        //query.include("objectId");
        query.whereEqualTo("CoursePointer", course);
        Log.i(TAG, course.getKeyCoursename());
        query.findInBackground(new FindCallback<Assignment>() {
            @Override
            public void done(List<Assignment> assignments, ParseException e) {
                for (Assignment assignment : assignments) {

                        Log.i(TAG, assignment.getKeyAssignmentname());

                }
                allAssignments.addAll(assignments);
                assignmentsAdapter.notifyDataSetChanged();
            }
        });
    }
}


