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

public class JobActivity extends AppCompatActivity {

    List<JobModel> jobList = new ArrayList<>();

    String url = "https://api.jsonserve.com/5nd8OZ";

    JobViewAdapter jobAdapter;
    RecyclerView jobRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        setTitle("CareerVista App");

        jobRecyclerView = findViewById(R.id.job_recycler_view);
        jobRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        jobRecyclerView.setHasFixedSize(true);

        TextView jobtextView = findViewById(R.id.text_view_3);
        jobtextView.setBackground(ContextCompat.getDrawable(this, R.drawable.border));

        // Get a reference to the app's ActionBar
        ActionBar actionBar = getSupportActionBar();

        // Set the background color of the title in the ActionBar
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor((R.color.colorTrendingStart)));
        actionBar.setBackgroundDrawable(colorDrawable);

        // Set the status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(getResources().getColor((R.color.colorTrendingStart)));
        }

        GetData();
    }

    private void GetData() {

        ProgressBar jobProgressBar;
        jobProgressBar = findViewById(R.id.Loading);

        // Show the progress bar
        jobProgressBar.setVisibility(View.VISIBLE);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response", response.toString());
                        try {
                            JSONArray jobs = response.getJSONArray("jobs");

                            for (int i = 0; i < jobs.length(); i++) {
                                JSONObject jobObject = jobs.getJSONObject(i);

                                JobModel job = new JobModel();

                                job.setTitle(jobObject.getString("job_title"));
                                job.setWork(jobObject.getString("work"));
                                job.setStart(jobObject.getString("application_start_date"));
                                job.setEnd(jobObject.getString("application_end_date"));
                                job.setSalary(jobObject.getString("expected_salary"));

                                jobList.add(job);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        jobAdapter = new JobViewAdapter(getApplicationContext(), jobList);
                        jobRecyclerView.setAdapter(jobAdapter);
                        jobAdapter.notifyDataSetChanged();

                        jobProgressBar.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(JobActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Content error", error.getMessage().toString());

                jobProgressBar.setVisibility(View.GONE);
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}