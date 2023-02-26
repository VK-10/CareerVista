package com.example.carrervista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.braintreepayments.api.DropInClient;
import com.braintreepayments.api.DropInListener;
import com.braintreepayments.api.DropInRequest;
import com.braintreepayments.api.DropInResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity implements DropInListener {

    List<CourseModel> courseList = new ArrayList<>();

    String url = "https://api.jsonserve.com/pftZDd";

    CourseViewAdapter adapter;
    RecyclerView recyclerView;
    DropInClient dropInClient;
    DropInRequest dropInRequest;
    String email;
    Button btn_buy;
    int REQUEST_CODE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        setTitle("CareerVista App");

        recyclerView = findViewById(R.id.course_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        REQUEST_CODE = 200;

        dropInRequest = new DropInRequest();
        dropInClient = new DropInClient(CourseActivity.this, dropInRequest, "sandbox_x6ffqwjg_hfmc2kjwttt653tg");

        TextView textView = findViewById(R.id.text_view_3);
        textView.setBackground(ContextCompat.getDrawable(this, R.drawable.border));

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

//        btn_buy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("Pressed","Lauching");
//                dropInClient.launchDropInForResult(CourseActivity.this,REQUEST_CODE);
//            }
//        });
}

        private void GetData() {

            ProgressBar myProgressBar;
            myProgressBar = findViewById(R.id.Loading);

            // Show the progress bar
            myProgressBar.setVisibility(View.VISIBLE);

            RequestQueue requestQueue = Volley.newRequestQueue(this);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("response", response.toString());
                            try {
                                JSONArray courses = response.getJSONArray("courses");

                                for (int i = 0; i < courses.length(); i++) {
                                    JSONObject courseObject = courses.getJSONObject(i);

                                    CourseModel course = new CourseModel();

                                    course.setName(courseObject.getString("name"));
                                    course.setDuration(courseObject.getString("duration"));
                                    course.setRating(courseObject.getString("rating"));
                                    course.setInstructor(courseObject.getString("instructor"));
                                    course.setImage(courseObject.getString("imageurl"));

                                    courseList.add(course);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            adapter = new CourseViewAdapter(getApplicationContext(), courseList);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            myProgressBar.setVisibility(View.GONE);

                            adapter.setOnButtonClickListener(new CourseViewAdapter.OnButtonClickListener() {
                                @Override
                                public void onButtonClicked(int position) {
                                    dropInClient.launchDropInForResult(CourseActivity.this, REQUEST_CODE);
                                }
                            });
                        }

                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Toast.makeText(CourseActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("Content error", error.getMessage().toString());

                    myProgressBar.setVisibility(View.GONE);
                }
            });
            requestQueue.add(jsonObjectRequest);
        }


    @Override
    public void onDropInSuccess(@NonNull DropInResult dropInResult) {
        String w=dropInResult.getPaymentMethodNonce().getString();
        Toast.makeText(CourseActivity.this,"Sucessfully Bought",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(CourseActivity.this,Welcome.class);
        startActivity(intent);
    }

    @Override
    public void onDropInFailure(@NonNull Exception error) {
        Toast.makeText(CourseActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
    }
}