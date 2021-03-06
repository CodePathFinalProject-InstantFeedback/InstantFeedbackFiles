package com.codepath.skc.instantfeedback.Fragments;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.skc.instantfeedback.CoursesAdapter;
import com.codepath.skc.instantfeedback.LoginActivity;
import com.codepath.skc.instantfeedback.R;
import com.parse.ParseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public static final String TAG = "StreamFragment";
    ImageView profilePic;
    TextView profileUserName;
    TextView profileUserType;
    Button btnLogOut;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profilePic=view.findViewById(R.id.profilePic);
        profileUserName=view.findViewById(R.id.profileUserName);
        btnLogOut=view.findViewById(R.id.btnLogOut);
        profileUserType=view.findViewById(R.id.profileUserType);


        profileUserName.setText(ParseUser.getCurrentUser().getUsername());
        profileUserType.setText(ParseUser.getCurrentUser().get("UserType").toString());


        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                Intent i=new Intent(getContext(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });
    }

}
