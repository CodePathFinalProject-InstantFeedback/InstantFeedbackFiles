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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.codepath.skc.instantfeedback.AddActivity;
import com.codepath.skc.instantfeedback.AssignmentsActivity;
import com.codepath.skc.instantfeedback.CoursesAdapter;
import com.codepath.skc.instantfeedback.Models.Course;
import com.codepath.skc.instantfeedback.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StreamFragment extends Fragment implements CoursesAdapter.OnCourseListener {

    public static final int REQUEST_CODE=42;
    public static final String TAG = "StreamFragment";
    RecyclerView rvCourses;
    public CoursesAdapter courseAdapter;
    protected List<Course> allCourses;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_stream2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvCourses = view.findViewById(R.id.rvCourses);
        allCourses = new ArrayList<>();
        rvCourses.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.i(TAG, "Inside Stream Fragment!");

        //Here I tried prepopulating, and commenting queryPosts() and it worked!
        /*Course course1=new Course();
        course1.setCourseName("course 1");
        course1.setInsturctor("Dr.B");
        course1.setCourseDescription("Important course");
        Course course2=new Course();
        course2.setCourseName("course 2");
        course2.setInsturctor("Dr.B");
        course2.setCourseDescription("Important course");
        allCourses.add(course1);
        allCourses.add(course2);*/
        //queryPosts();
        courseAdapter = new CoursesAdapter(getContext(), allCourses, this);
        rvCourses.setAdapter(courseAdapter);
        queryPosts();
        Log.i(TAG,"all course"+allCourses);
        //courseAdapter.setCourses(allCourses);
    }

    protected void queryPosts() {

        ParseQuery<Course> query = ParseQuery.getQuery(Course.class);
        //query.include(Course.KEY_USER);
        //query.addDescendingOrder(Post.CREATED_AT);
        query.findInBackground(new FindCallback<Course>() {
            @Override
            public void done(List<Course> courses, ParseException e) {

                if (e != null) {
                    Log.e(TAG, "Not getting Posts!", e);
                }

                for (Course course : courses) {
                    Log.i(TAG, "CourseName:" + course.getKeyCoursename() + ",Description" + course.getKeyCoursedescription());
                }
                //allCourses.addAll(courses);
                allCourses.addAll(courses);
                courseAdapter.setCourses(courses);
                courseAdapter.notifyDataSetChanged();
                Log.i(TAG, "The courses full list is" + courseAdapter.courses);
            }
        });
    }

    @Override
    public void onCourseClick(int position) {
        Course course = allCourses.get(position);
        //Toast.makeText(MainActivity.this, "In Home", Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), course.getKeyCoursename(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), AssignmentsActivity.class);
        intent.putExtra("course", Parcels.wrap(course));
        startActivity(intent);
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //Toast.makeText(getContext(), "Inside this fragment updated!", Toast.LENGTH_SHORT).show();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_add, menu);
        //MenuItem searchItem = menu.findItem(R.id.search);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.add) {
            if (ParseUser.getCurrentUser().get("UserType").toString().toLowerCase().equals("professor"))
            {
                //Toast.makeText(this, "compose!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getContext(),AddActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
                return true;
            }

            else {

                Toast.makeText(getContext(), "Student cannot add a new course! ", Toast.LENGTH_SHORT).show();

            }


        }

        else if(item.getItemId()==R.id.search)
         {

            SearchView searchView = (SearchView) item.getActionView();
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    Toast.makeText(getContext(), newText, Toast.LENGTH_SHORT).show();
                    courseAdapter.getFilter().filter(newText);
                    return false;
                }

            });
    }
        return super.onOptionsItemSelected(item);
    }

}