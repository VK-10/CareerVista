package com.example.carrervista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BoughtCourse extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Courses> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bought_course);

         recyclerView=findViewById(R.id.bought_course);
         list=new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference userRef = database.getReference().child("usercourse").child(userId);

        ValueEventListener coursesListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Loop through all child nodes under the current user's "Courses" node
                for (DataSnapshot courseSnapshot : dataSnapshot.getChildren()) {
                    // Get the Course object from the dataSnapshot
                    Courses course = courseSnapshot.child("Courses").getValue(Courses.class);
                    list.add(course);
                    Log.d("Courses",course.toString());

                }
                ArrayAdapter arrayAdapter=new ArrayAdapter(list);
                recyclerView.setLayoutManager(new LinearLayoutManager(BoughtCourse.this));
                recyclerView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        userRef.addListenerForSingleValueEvent(coursesListener);






    }
}