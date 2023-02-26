package com.example.carrervista;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ScholarActivity extends AppCompatActivity {
    ArrayList<ModelClass> scholarshipsList;
    RecyclerView recyclerView;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scholar);

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

        RequestQueue queue = Volley.newRequestQueue(this);

        scholarshipsList = new ArrayList<>();
        String url = "https://api.jsonserve.com/XBsHrX";

        // Request a JSON response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {

                    Log.d("response",response.toString());
                    // Parse the JSON response
                    try {
                        JSONArray scholarshipsArray = response.getJSONArray("scholarships");

                        for (int i = 0; i < scholarshipsArray.length(); i++) {
                            JSONObject scholarshipObject = scholarshipsArray.getJSONObject(i);

                            ModelClass scholarship = new ModelClass();
                            scholarship.setTextview1(scholarshipObject.getString("name"));
                            scholarship.setTextview3(scholarshipObject.getString("provider"));
                            scholarship.setTextview2(scholarshipObject.getString("start_date"));
                            scholarship.setTextView4(scholarshipObject.getString("end_date"));
                            scholarship.setTextView5(scholarshipObject.getString("amount"));
                            scholarship.setTextView6(scholarshipObject.getString("Eligibility"));
                            scholarship.setTextView7(scholarshipObject.getString("url"));

                            scholarshipsList.add(scholarship);
                        }

                        recyclerView=findViewById(R.id.recyclerview);

                        LinearLayoutManager layoutManager = new LinearLayoutManager(ScholarActivity.this, LinearLayoutManager.VERTICAL,false);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        adapter = new Adapter(ScholarActivity.this,scholarshipsList);
                        recyclerView.setAdapter(adapter);

                        // Do something with the scholarshipsList
                        // ...

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
                    // Handle error
                    error.printStackTrace();
                });

        queue.add(jsonObjectRequest);

//        initRecyclerView();





    }
    private void initRecyclerView() {

        Log.d("Size of array", String.valueOf(scholarshipsList.size()));
        recyclerView=findViewById(R.id.recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(ScholarActivity.this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new Adapter(ScholarActivity.this,scholarshipsList);
        recyclerView.setAdapter(adapter);

    }
}