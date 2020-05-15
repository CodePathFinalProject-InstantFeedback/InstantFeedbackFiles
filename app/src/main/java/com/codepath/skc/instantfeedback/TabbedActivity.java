package com.codepath.skc.instantfeedback;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.codepath.skc.instantfeedback.Models.Course;
import com.google.android.material.tabs.TabLayout;

import org.parceler.Parcels;

public class TabbedActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Course course;

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
}
