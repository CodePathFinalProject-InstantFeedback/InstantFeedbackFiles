package com.codepath.skc.instantfeedback;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.codepath.skc.instantfeedback.Fragments.AssignmentBarChartFragment;
import com.codepath.skc.instantfeedback.Fragments.AssignmentLineChartFragment;
import com.codepath.skc.instantfeedback.Models.Assignment;


class TabsAdapter  extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public TabsAdapter (Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AssignmentBarChartFragment assignmentBarChartFragment = new AssignmentBarChartFragment();
                return assignmentBarChartFragment;
            case 1:
                AssignmentLineChartFragment assignmentLineChartFragment=new AssignmentLineChartFragment();
                return assignmentLineChartFragment;
            default:
                AssignmentBarChartFragment assignmentBarChartFragment2 = new AssignmentBarChartFragment();
                return assignmentBarChartFragment2;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}

