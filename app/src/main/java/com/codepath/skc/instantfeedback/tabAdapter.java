package com.codepath.skc.instantfeedback;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import com.codepath.skc.instantfeedback.Fragments.AssignmentBarChartFragment;
import com.codepath.skc.instantfeedback.Fragments.AssignmentLineChartFragment;
import com.codepath.skc.instantfeedback.Models.Course;

public class tabAdapter extends FragmentPagerAdapter {

    Context context;
    int totalTabs;
    Course course;

    public tabAdapter(@NonNull FragmentManager fm, int behavior, Context c, int totalTabs, Course course) {
        super(fm,behavior);
        context = c;
        this.totalTabs = totalTabs;
        this.course=course;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                AssignmentBarChartFragment assignmentBarChartFragment = new AssignmentBarChartFragment();
                assignmentBarChartFragment.setCourse(course);
                return assignmentBarChartFragment;
            case 1:
                AssignmentLineChartFragment assignmentLineChartFragment = new AssignmentLineChartFragment();
                assignmentLineChartFragment.setCourse(course);
                return assignmentLineChartFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

}
