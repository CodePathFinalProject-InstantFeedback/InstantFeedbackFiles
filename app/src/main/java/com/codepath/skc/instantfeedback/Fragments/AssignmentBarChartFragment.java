package com.codepath.skc.instantfeedback.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codepath.skc.instantfeedback.AssignmentsAdapter;
import com.codepath.skc.instantfeedback.Models.Assignment;
import com.codepath.skc.instantfeedback.Models.Course;
import com.codepath.skc.instantfeedback.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AssignmentBarChartFragment extends Fragment {

    public static final String TAG = "AssignmentBarChart";
    public String objectId;
    Course course;
    RecyclerView rvAssignments;
    List<Assignment> allAssignments;
    AssignmentsAdapter assignmentsAdapter;

    public AssignmentBarChartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_assignment_bar_chart, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getContext(), "Inside Bar Chart Fragment!! ", Toast.LENGTH_SHORT).show();
        rvAssignments=view.findViewById(R.id.rvAssignments);
        allAssignments = new ArrayList<>();
        assignmentsAdapter = new AssignmentsAdapter(getContext(), allAssignments);
        rvAssignments.setAdapter(assignmentsAdapter);
        rvAssignments.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();

    }


    protected void queryPosts() {
        ParseQuery<Assignment> query = ParseQuery.getQuery(Assignment.class);
        //query.include("objectId");
        query.whereEqualTo("CoursePointer", course);
        query.addAscendingOrder("indexNumber");
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


    public void setCourse(Course course) {
        this.course = course;

    }

    }
