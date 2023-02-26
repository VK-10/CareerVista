package com.example.carrervista;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArrayAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Courses> courses;

    public ArrayAdapter(List<Courses> courses) {
        this.courses = courses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.bought,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Courses course = courses.get(position);
        holder.courseTitle.setText(course.getTitle());
        holder.courseDuration.setText(course.getDuration());
        holder.courseInstructor.setText(course.getInstructor());
        holder.courseRating.setText(course.getRating());

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}

class ViewHolder extends RecyclerView.ViewHolder {

    public ImageView courseImage;
    public TextView courseTitle;
    public TextView courseDuration;
    public TextView courseInstructor;
    public TextView courseRating;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        courseImage = itemView.findViewById(R.id.course_image);
        courseTitle = itemView.findViewById(R.id.bought_title);
        courseDuration = itemView.findViewById(R.id.bought_duration);
        courseInstructor = itemView.findViewById(R.id.bought_instructor);
        courseRating = itemView.findViewById(R.id.bought_rating);
    }
}
