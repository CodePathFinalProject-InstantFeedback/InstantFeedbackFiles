package com.codepath.skc.instantfeedback;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.codepath.skc.instantfeedback.Models.Assignment;
import com.codepath.skc.instantfeedback.Models.Course;
import org.parceler.Parcels;

public class AddAssignActivity extends AppCompatActivity {

    EditText adAssignmentName;
    EditText adAssignmentDeadline;
    EditText adAssignmentDescription;
    Button  btnAssignAdd;
    Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assign);
        adAssignmentName=findViewById(R.id.adAssignmentName);
        adAssignmentDeadline=findViewById(R.id.adAssignmentDeadline);
        adAssignmentDescription=findViewById(R.id.adAssignmentDescription);
        btnAssignAdd=findViewById(R.id.btnAssignAdd);
        Intent i = getIntent();
        course = Parcels.unwrap(i.getParcelableExtra("course"));
        btnAssignAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Assignment assignment=new Assignment();
                assignment.setKeyAssignmentname(adAssignmentName.getText().toString());
                assignment.setKeyAssignmentdescription(adAssignmentDescription.getText().toString());
                assignment.setNumberOfRatings(0);
                assignment.setKeyCoursePointer(course);
                assignment.setKeyTotalrating(0);
                assignment.saveInBackground();
                showToast();

            }
        });
    }

    private void showToast() {
        Toast.makeText(this, "Added Assignment",Toast.LENGTH_SHORT).show();
    }


}


