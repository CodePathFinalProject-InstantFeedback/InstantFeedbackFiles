package com.codepath.skc.instantfeedback;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.skc.instantfeedback.Models.Course;

import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder> {

    private Context context;
    private List<Course> courses;

    public CoursesAdapter(Context context, List<Course> courses){
            this.context=context;
            this.courses=courses;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_stream,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Course course=courses.get(position);
        holder.bind(course);
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }


    class ViewHolder extends  RecyclerView.ViewHolder
    {
        private TextView etCourseName;
        private TextView etInstructor;
        private TextView etCourseDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etCourseName=itemView.findViewById(R.id.etCourseName);
            etInstructor=itemView.findViewById(R.id.etInstructor);
            etCourseDescription=itemView.findViewById(R.id.etCourseDescription);
        }

        public void bind(Course course) {
            etCourseName.setText(course.getKeyCoursename());
            etInstructor.setText(course.getKeyInstructor());
            etCourseDescription.setText(course.getKeyCoursedescription());
        }
    }



}
