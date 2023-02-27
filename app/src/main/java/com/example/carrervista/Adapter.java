package com.example.carrervista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    ArrayList<ModelClass> userlist;
    Context context;

    public Adapter (Context context, ArrayList<ModelClass>userlist){
        this.userlist=userlist;
        this.context = context;
    };


    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        String name = userlist.get(position).getTextview1();
        String provider  = userlist.get(position).getTextview2();
        String startdate = userlist.get(position).getTextview3();
        String enddate = userlist.get(position).getTextView4();
        String amount=userlist.get(position).getTextView5();
        String eligibility=userlist.get(position).getTextView6();


        holder.setData(name,provider,startdate,enddate,amount,eligibility);
    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textView1;
        private TextView textView2;
        private TextView textview3;
        private TextView textview4;
        private TextView textview5;

        private TextView textview6;
        private Button btn_full_course;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setBackground(itemView.getContext().getDrawable(R.drawable.black_background_border));

            textView1=itemView.findViewById(R.id.txt_scholarship_name);
            textView2 = itemView.findViewById(R.id.txt_provider_name);
            textview3= itemView.findViewById(R.id.txt_application_start_date);
            textview4= itemView.findViewById(R.id.txt_application_end_date);
            textview5= itemView.findViewById(R.id.txt_amount);
            textview6=itemView.findViewById(R.id.txt_elegibility);
            btn_full_course=itemView.findViewById(R.id.btnfull_course);

        }

        public void setData( String name, String provider, String startdate, String enddate,String amount,String eligibility) {
            textView1.setText(name);
            textview3.setText(provider);
            textView2.setText(startdate);
            textview4.setText(enddate);
            textview5.setText(amount);
            textview6.setText(eligibility);

        }
    }
}