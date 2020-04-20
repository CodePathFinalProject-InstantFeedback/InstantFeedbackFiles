package com.codepath.skc.instantfeedback;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codepath.skc.instantfeedback.Models.Course;

public class AddActivity extends AppCompatActivity {


    EditText adCourseName;
    EditText adInsturctor;
    EditText adCourseDescription;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toast.makeText(this, "Inside add activity", Toast.LENGTH_SHORT).show();
        getSupportActionBar().setTitle("Add Course");
        adCourseName=findViewById(R.id.adCourseName);
        adInsturctor=findViewById(R.id.adInstructor);
        adCourseDescription=findViewById(R.id.adCourseDescription);
        btnAdd=findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course course=new Course();
                course.setCourseName(adCourseName.getText().toString());
                course.setInsturctor(adInsturctor.getText().toString());
                course.setCourseDescription(adCourseDescription.getText().toString());
                course.saveInBackground();
            }
        });

    }

}
