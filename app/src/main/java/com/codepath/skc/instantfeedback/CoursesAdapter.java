package com.codepath.skc.instantfeedback;

import android.content.Context;
import android.util.Log;
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
    private OnCourseListener onCourseListener;

    public CoursesAdapter(Context context, List<Course> courses,OnCourseListener onCourseListener){
            this.context=context;
            this.courses=courses;
            this.onCourseListener=onCourseListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_stream,parent,false);
        return new ViewHolder(view,onCourseListener);
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


     class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView etCourseName;
        private TextView etInstructor;
        private TextView etCourseDescription;
        OnCourseListener onCourseListener;

        public ViewHolder(@NonNull View itemView,OnCourseListener onCourseListener) {
            super(itemView);
            etCourseName=itemView.findViewById(R.id.etCourseName);
            etInstructor=itemView.findViewById(R.id.etInstructor);
            etCourseDescription=itemView.findViewById(R.id.etCourseDescription);
            this.onCourseListener=onCourseListener;
            itemView.setOnClickListener(this);
        }

        public void bind(Course course) {
            etCourseName.setText(course.getKeyCoursename());
            etInstructor.setText(course.getKeyInstructor());
            etCourseDescription.setText(course.getKeyCoursedescription());
        }

        @Override
        public void onClick(View v) {
            onCourseListener.onCourseClick(getAdapterPosition());
        }
    }

    public interface  OnCourseListener{
        void onCourseClick(int position);
    }


}
