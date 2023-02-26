package com.example.carrervista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MentorAdapter extends RecyclerView.Adapter<MentorAdapter.ViewHolder> {

    List<MentorClass> userList;
    Context context;

    public MentorAdapter(Context context, List<MentorClass> userList){
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemdesign,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textview1.setText(userList.get(position).getTextview1());
        holder.textview2.setText(userList.get(position).getTextview2());
        holder.textview3.setText(userList.get(position).getTextview3());
        holder.textview4.setText(userList.get(position).getTextview4());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageview;
        TextView textview1;
        TextView textview2;
        TextView textview3;
        TextView textview4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setBackground(itemView.getContext().getDrawable(R.drawable.border));

            imageview = itemView.findViewById(R.id.mentor_image);
            textview1 = itemView.findViewById(R.id.mentor_name);
            textview2 = itemView.findViewById(R.id.mentor_experience);
            textview3 = itemView.findViewById(R.id.mentor_course);
            textview4 = itemView.findViewById(R.id.mentor_specialization);
        }
    }
}