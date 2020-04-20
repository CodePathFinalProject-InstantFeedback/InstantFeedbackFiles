package com.codepath.skc.instantfeedback;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.skc.instantfeedback.Models.Course;

import java.util.ArrayList;
import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<Course> courses;
    private  List<Course> coursesfull;
    private OnCourseListener onCourseListener;

    public CoursesAdapter(Context context, List<Course> courses,OnCourseListener onCourseListener){
            this.context=context;
            this.courses=courses;
            coursesfull=new ArrayList<>(courses);
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

    @Override
    public Filter getFilter() {
        return courseFilter;
    }

    private Filter courseFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Course> filteredList=new ArrayList<>();
            if (constraint == null || constraint.length() == 0)
            {
                filteredList.addAll(coursesfull);
            }
            else{
                String filterPattern=constraint.toString().toLowerCase().trim();
                for (Course course: coursesfull){
                    if (course.getKeyCoursename().contains(constraint))
                    {
                        filteredList.add(course);
                    }
                }
            }

            FilterResults results=new FilterResults();
            results.values=filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            courses.clear();
            courses.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

}
