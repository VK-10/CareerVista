package com.example.carrervista;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MentorActivity extends AppCompatActivity{
    RecyclerView recyclerview;
    List<MentorClass> userList = new ArrayList<>();
    MentorAdapter mentorAdapter;

    String url = "https://api.jsonserve.com/cC70S4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor);

        setTitle("CareerVista App");

        recyclerview =findViewById(R.id.mentor_recycler_view);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setHasFixedSize(true);

        TextView jobtextView = findViewById(R.id.text_view_3);
        jobtextView.setBackground(ContextCompat.getDrawable(this, R.drawable.border));

        // Get a reference to the app's ActionBar
        ActionBar actionBar = getSupportActionBar();

        // Set the background color of the title in the ActionBar
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor((R.color.colorTrendingStart)));
        assert actionBar != null;
        actionBar.setBackgroundDrawable(colorDrawable);

        // Set the status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(getResources().getColor((R.color.colorTrendingStart)));
        }

        initData();
    }

    public void initData() {

        ProgressBar myProgressBar;
        myProgressBar = findViewById(R.id.Loading);

        // Show the progress bar
        myProgressBar.setVisibility(View.VISIBLE);

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString());
                        try {
                            JSONArray mentors = response.getJSONArray("mentors");

                            for (int i = 0; i < mentors.length(); i++) {
                                JSONObject mentorObject = mentors.getJSONObject(i);

                                MentorClass mentor = new MentorClass();

                                mentor.setTextview1(mentorObject.getString("mentor_name"));
                                mentor.setTextview2(mentorObject.getString("experience"));
                                mentor.setTextview3(mentorObject.getString("course_name"));
                                mentor.setTextview4(mentorObject.getString("mentor_specialization"));

                                userList.add(mentor);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        mentorAdapter = new MentorAdapter(getApplicationContext(), userList);
                        recyclerview.setAdapter(mentorAdapter);
                        mentorAdapter.notifyDataSetChanged();

                        myProgressBar.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(MentorActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Content error", error.getMessage());

                myProgressBar.setVisibility(View.GONE);
            }
        });
        queue.add(jsonObjectRequest);
    }
}