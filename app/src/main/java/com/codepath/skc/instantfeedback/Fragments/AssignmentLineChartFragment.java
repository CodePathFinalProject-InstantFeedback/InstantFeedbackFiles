package com.codepath.skc.instantfeedback.Fragments;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codepath.skc.instantfeedback.AssignmentsAdapter;
import com.codepath.skc.instantfeedback.Models.Assignment;
import com.codepath.skc.instantfeedback.Models.Course;
import com.codepath.skc.instantfeedback.R;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

public class AssignmentLineChartFragment extends Fragment {

    public static final String TAG = "AssignmentLineChart";
    Course course;
    LineChart chart;
    List<Assignment> allAssignments;
    View glView;

    public AssignmentLineChartFragment() {
        // Required empty public constructor

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_assignment_line_chart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        chart=view.findViewById(R.id.lineChart);
        Toast.makeText(getContext(), "Inside Line Chart Fragment!!!! ", Toast.LENGTH_SHORT).show();
        allAssignments=new ArrayList<>();
        glView=view;
        queryPosts();
    }

    public void setCourse(Course course) {
        Log.i(TAG,"The name of the assignment is"+course.getKeyCoursename());
        this.course = course;

    }

    protected void queryPosts() {
        ParseQuery<Assignment> query = ParseQuery.getQuery(Assignment.class);
        //query.include("objectId");
        query.whereEqualTo("CoursePointer", course);
        //Log.i(TAG, course.getKeyCoursename());
        query.findInBackground(new FindCallback<Assignment>() {
            @Override
            public void done(List<Assignment> assignments, ParseException e) {
                for (Assignment assignment : assignments) {

                    Log.i(TAG, assignment.getKeyAssignmentname());

                }

                allAssignments.addAll(assignments);
                plot();
            }
        });
    }



    private void plot() {
        Log.i(TAG,"The assignments list is"+allAssignments);;
        List<String> xAxes=new ArrayList<>();
        List<Entry> angerEntries = new ArrayList<>();
        List<Entry> joyEntries = new ArrayList<>();
        List<Entry> fearEntries=new ArrayList<>();
        List<Entry> sadnessEntries=new ArrayList<>();

        int counter=0;
        for (Assignment assignment: allAssignments)
        {
            Log.i(TAG,"The after udpate list is"+counter+" "+assignment.getKeyGetangerval());
            angerEntries.add(new Entry(counter, assignment.getKeyGetangerval()));
            joyEntries.add(new Entry(counter,assignment.getKeyGetjoyval()));
            fearEntries.add(new Entry(counter,assignment.getKeyGetfearval()));
            sadnessEntries.add(new Entry(counter,assignment.getKeyGetsadnessval()));
            counter+=1;
        }

        //dataSet.setValueTextColor(); // styling, ..


        LineDataSet angerDataset= new LineDataSet(angerEntries, "Anger"); // add entries to dataset
        angerDataset.setColor(glView.getResources().getColor(R.color.Red));
        angerDataset.setLineWidth(2.5f);
        angerDataset.setValueTextSize(2.5f);
        angerDataset.setAxisDependency(YAxis.AxisDependency.LEFT);


        LineDataSet joyDataset = new LineDataSet(joyEntries, "Joy"); // add entries to dataset
        joyDataset.setColor(glView.getResources().getColor(R.color.Joy));
        joyDataset.setLineWidth(2.5f);
        joyDataset.setValueTextSize(2.5f);
        joyDataset.setAxisDependency(YAxis.AxisDependency.LEFT);


        LineDataSet fearDataset= new LineDataSet(fearEntries, "Fear"); // add entries to dataset
        fearDataset.setColor(glView.getResources().getColor(R.color.Fear));
        fearDataset.setLineWidth(2.5f);
        fearDataset.setValueTextSize(2.5f);
        fearDataset.setAxisDependency(YAxis.AxisDependency.LEFT);


        LineDataSet sadnessDataset = new LineDataSet(sadnessEntries, "Sadness"); // add entries to dataset
        sadnessDataset.setColor(glView.getResources().getColor(R.color.Sadness));
        sadnessDataset.setLineWidth(2.5f);
        sadnessDataset.setValueTextSize(2.5f);
        sadnessDataset.setAxisDependency(YAxis.AxisDependency.LEFT);


        List<ILineDataSet> datasets=new ArrayList<>();
        datasets.add(angerDataset);
        datasets.add(joyDataset);
        datasets.add(fearDataset);
        datasets.add(sadnessDataset);

        LineData lineData = new LineData(datasets);
        //MyXAxisFormatter formatter=new MyXAxisFormatter();
        //formatter.setAssign(allAssignments);
        //formatter.populateLabel();
        //chart.getXAxis().setAxisMinimum(-0.5f);
        //chart.getXAxis().setAxisMaximum(chart.getBarData().getBarWidth()*4);
        //chart.getXAxis().setValueFormatter(formatter);
        chart.getXAxis().setGranularity(1f);
        chart.getXAxis().setGranularityEnabled(true);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.setData(lineData);
        chart.invalidate(); // refresh
    }


    class MyXAxisFormatter extends ValueFormatter {

        List<Assignment> assignments1=new ArrayList<>();
        List<String> labels=new ArrayList<>();

        public void populateLabel() {
            int counter=0;
            for (Assignment assignment : assignments1) {
                labels.add(String.valueOf(counter));
                counter+=1;
            }
        }

        @Override
        public String getAxisLabel(float value, AxisBase axis) {

            return labels.get(Math.round(value));

        }

        public void setAssign(List<Assignment> assignments){
            assignments1.addAll(assignments);
        }
    }



}
