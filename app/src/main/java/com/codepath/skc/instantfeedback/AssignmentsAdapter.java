package com.codepath.skc.instantfeedback;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.skc.instantfeedback.Models.Assignment;
import com.codepath.skc.instantfeedback.Models.Course;
import com.parse.ParseUser;

import java.util.List;

public class AssignmentsAdapter extends RecyclerView.Adapter<AssignmentsAdapter.ViewHolder> {

    public static final String TAG = "AssignmentAdapter";
    private String UserType=ParseUser.getCurrentUser().get("UserType").toString();
    private Context context;
    private List<Assignment> assignments;

    public AssignmentsAdapter(Context context, List<Assignment> assignments){
        this.context=context;
        this.assignments=assignments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (UserType.equals("Student")) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_assignments, parent, false);
            return new ViewHolder(view);
        }

        else{
            View view = LayoutInflater.from(context).inflate(R.layout.item_assignments, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Assignment assignment=assignments.get(position);
        holder.bind(assignment);
    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }


    class ViewHolder extends  RecyclerView.ViewHolder  {
        private TextView etAssignmentName;
        private float totalRating;
        private int newTotal;
        private  float currentRating;
        private TextView etDeadline;
        private RatingBar etRatingBar;
        private Button etSubmitRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etAssignmentName = itemView.findViewById(R.id.etAssignmentName);
            etDeadline = itemView.findViewById(R.id.etDeadline);
            etRatingBar = itemView.findViewById(R.id.etratingBar);
            etSubmitRating=itemView.findViewById(R.id.etSubmitRating);
        }

        public void bind(final Assignment assignment) {
            etAssignmentName.setText(assignment.getKeyAssignmentname());
            etRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    currentRating=rating;
                }
            });

            etSubmitRating.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newTotal=assignment.getNumberOfRatings()+1;
                    Log.i(TAG,String.valueOf(newTotal));
                    totalRating=currentRating+assignment.getKeyTotalrating();
                    Log.i(TAG,"The new average rating amount is"+(totalRating/newTotal));
                    assignment.setNumberOfRatings(newTotal);
                    assignment.setKeyAveragerating(Math.round(totalRating));
                    assignment.saveInBackground();
                }
            });

        }


    }

}