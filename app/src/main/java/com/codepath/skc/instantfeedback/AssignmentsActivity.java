package com.codepath.skc.instantfeedback;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.codepath.skc.instantfeedback.Models.Assignment;
import com.codepath.skc.instantfeedback.Models.Course;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.add) {
            if (ParseUser.getCurrentUser().get("UserType").toString().toLowerCase().equals("professor")) {
                //Toast.makeText(this, "compose!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, AddAssignActivity.class);
                intent.putExtra("course", Parcels.wrap(course));
                startActivity(intent);
                finish();
                return true;
            } else {

                Toast.makeText(this, "Student cannot add a new assignemnt! ", Toast.LENGTH_SHORT).show();

            }
        }
        return true;
    }
}


