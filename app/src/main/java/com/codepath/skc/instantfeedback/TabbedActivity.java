package com.codepath.skc.instantfeedback;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codepath.skc.instantfeedback.Models.Assignment;
import com.codepath.skc.instantfeedback.Models.Course;
import com.google.android.material.tabs.TabLayout;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.List;

public class TabbedActivity extends AppCompatActivity {

    public static final String TAG = "TabbedActivity";
    TabLayout tabLayout;
    ViewPager viewPager;
    Course course;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("microview"));
        tabLayout.addTab(tabLayout.newTab().setText("macroview"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        course = Parcels.unwrap(getIntent().getParcelableExtra("course"));
        findIndex();
        getSupportActionBar().setTitle(course.getKeyCoursename());
        final tabAdapter adapter = new tabAdapter(getSupportFragmentManager(),1, this, tabLayout.getTabCount(),course);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
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
                intent.putExtra("intVal",index);
                startActivity(intent);
                finish();
                return true;
            } else {

                Toast.makeText(this, "Student cannot add a new assignemnt! ", Toast.LENGTH_SHORT).show();

            }
        }
        return true;
    }

    protected void findIndex() {
        ParseQuery<Assignment> query = ParseQuery.getQuery(Assignment.class);
        //query.include("objectId");
        query.whereEqualTo("CoursePointer", course);
        query.findInBackground(new FindCallback<Assignment>() {
            @Override
            public void done(List<Assignment> assignments, ParseException e) {
                Log.i(TAG,"For course name"+course.getKeyCoursename()+"size is"+assignments.size());
                index=assignments.size();
            }
        });
    }

}
