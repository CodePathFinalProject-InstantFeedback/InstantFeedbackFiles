package com.codepath.skc.instantfeedback.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codepath.skc.instantfeedback.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AssignmentBarChartFragment extends Fragment {


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

    }



}
