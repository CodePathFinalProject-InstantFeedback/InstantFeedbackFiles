package com.codepath.skc.instantfeedback.Fragments;


import android.content.Intent;
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

import com.codepath.skc.instantfeedback.AssignmentsActivity;
import com.codepath.skc.instantfeedback.CoursesAdapter;
import com.codepath.skc.instantfeedback.Models.Course;
import com.codepath.skc.instantfeedback.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StreamFragment extends Fragment implements CoursesAdapter.OnCourseListener {


    public static final String TAG="StreamFragment";
    RecyclerView rvCourses;
    protected CoursesAdapter courseAdapter;
    protected List<Course> allCourses;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stream2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvCourses=view.findViewById(R.id.rvCourses);
        allCourses=new ArrayList<>();
        courseAdapter=new CoursesAdapter(getContext(),allCourses,this);
        rvCourses.setAdapter(courseAdapter);
        rvCourses.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.i(TAG,"Inside Stream Fragment!");
        queryPosts();
    }

    protected void queryPosts() {
        ParseQuery<Course> query= ParseQuery.getQuery(Course.class);
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
                allCourses.addAll(courses);
                courseAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onCourseClick(int position) {
        Course course=allCourses.get(position);
        //Toast.makeText(MainActivity.this, "In Home", Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(),course.getKeyCoursename(),Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(getContext(), AssignmentsActivity.class);
        intent.putExtra("course", Parcels.wrap(course));
        startActivity(intent);
    }
}
