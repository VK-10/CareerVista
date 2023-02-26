package com.example.carrervista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.braintreepayments.api.DropInClient;
import com.braintreepayments.api.DropInListener;
import com.braintreepayments.api.DropInRequest;
import com.braintreepayments.api.DropInResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Welcome extends AppCompatActivity implements DropInListener {

    String name;
    TextView txt_name;
    Button btn_click,btn_bought_course;
    DropInClient dropInClient;
    DropInRequest dropInRequest;
    String email;
    LinearLayout linearLayoutclick;
    LinearLayout linearLayoutclickcourse,linearclickjob;

    private FirebaseAuth mAuth;
    private DatabaseReference mDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

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

        txt_name=findViewById(R.id.textUser);
        setusername();

        int REQUEST_CODE=200;

        email=getIntent().getStringExtra("email");
        linearLayoutclick=findViewById(R.id.scholarship);
        linearLayoutclickcourse=findViewById(R.id.course);
        linearclickjob=findViewById(R.id.layoutJobs);

        linearLayoutclickcourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Welcome.this,CourseActivity.class);
                startActivity(intent);
            }
        });


        linearLayoutclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Welcome.this, ScholarActivity.class);
                startActivity(intent);

            }
        });

        linearclickjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Welcome.this,JobActivity.class);
                startActivity(intent);
            }
        });

        dropInRequest=new DropInRequest();
        dropInClient= new DropInClient(Welcome.this,dropInRequest, "sandbox_x6ffqwjg_hfmc2kjwttt653tg");

//        btn_click=findViewById(R.id.on_click);
//        btn_bought_course=findViewById(R.id.button_bought_course);
//
//        dropInClient.setListener(this);
//
//        btn_click.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dropInClient.launchDropInForResult(Welcome.this,REQUEST_CODE);
//
//            }
//        });
//
//        btn_bought_course.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(Welcome.this,BoughtCourse.class);
//                startActivity(intent);
//            }
//        });


    }

    @Override
    public void onDropInSuccess(@NonNull DropInResult dropInResult) {
        String w=dropInResult.getPaymentMethodNonce().getString();
        Toast.makeText(Welcome.this,w,Toast.LENGTH_SHORT).show();
        addcoursedata(email);


    }

    private void addcoursedata(String email) {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        String userId=FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference userref=database.getReference().child("usercourse").child(userId);

        String key1= userref.push().getKey();
        userref.child(key1).child("Courses").setValue(new Courses("233","Java Programming","2 hrs","4*","Dr . John",email));
        String key2= userref.push().getKey();
        userref.child(key2).child("Courses").setValue(new Courses("234","C++ Programming","2 hrs","4*","Dr . John",email));
        String Key3=userref.push().getKey();
        userref.child(Key3).child("Courses").setValue(new Courses("235","Python Programming","2 hrs","4*","Dr . John",email));


    }

    @Override
    public void onDropInFailure(@NonNull Exception error) {
        Toast.makeText(Welcome.this,error.getMessage(),Toast.LENGTH_SHORT).show();

    }

    public void setusername()
    {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        String userId=FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference userref=database.getReference().child("Clients").child(userId);

        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String name=snapshot.child("name").getValue(String.class);
                txt_name.setText(name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        userref.addListenerForSingleValueEvent(valueEventListener);
    }
}