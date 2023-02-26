package com.example.carrervista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JobViewAdapter extends RecyclerView.Adapter<JobViewAdapter.ViewHolder> {
    List<JobModel> jobList;
    Context context;

    public JobViewAdapter(Context context, List<JobModel> jobList) {
        this.context = context;
        this.jobList = jobList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create View
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_jobitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(jobList.get(position).getTitle());
        holder.work.setText(jobList.get(position).getWork());
        holder.start.setText((jobList.get(position).getStart()));
        holder.end.setText(jobList.get(position).getEnd());
        holder.salary.setText(jobList.get(position).getSalary());
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Initialize Variables
        TextView title, work, start, end, salary;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setBackground(itemView.getContext().getDrawable(R.drawable.border));

            // Assign Variable
            title = itemView.findViewById(R.id.title);
            work = itemView.findViewById(R.id.work);
            start = itemView.findViewById(R.id.start);
            end = itemView.findViewById(R.id.end);
            salary = itemView.findViewById(R.id.salary);
        }
    }
}