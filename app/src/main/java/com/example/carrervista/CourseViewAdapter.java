package com.example.carrervista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CourseViewAdapter extends RecyclerView.Adapter<CourseViewAdapter.ViewHolder> {
    List<CourseModel> courseList;
    Context context;

    public CourseViewAdapter(Context context, List<CourseModel> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    private OnButtonClickListener listener;

    public interface OnButtonClickListener {
        void onButtonClicked(int position);
    }

    public void setOnButtonClickListener(OnButtonClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create View
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_courseitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(courseList.get(position).getName());
        holder.duration.setText(courseList.get(position).getDuration());
        holder.instructor.setText((courseList.get(position).getInstructor()));
        holder.rating.setText(courseList.get(position).getRating());

        CourseModel item = courseList.get(position);
        String imageUrl = item.getImage();

        Glide.with(holder.image.getContext())
                .load(imageUrl)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Initialize Variables
        ImageView image;
        TextView name, duration, instructor, rating;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setBackground(itemView.getContext().getDrawable(R.drawable.border));

            // Assign Variable
            image = itemView.findViewById(R.id.course_image);
            name = itemView.findViewById(R.id.name);
            duration = itemView.findViewById(R.id.duration);
            instructor = itemView.findViewById(R.id.instructor);
            rating = itemView.findViewById(R.id.rating);
            button = itemView.findViewById(R.id.buy_button);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        listener.onButtonClicked(position);
                    }
                }
            });
        }
    }
}
