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
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

public class login extends AppCompatActivity {

    EditText email;
    EditText password;
    Button btn_login;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

        email=findViewById(R.id.usermail);
        password=findViewById(R.id.password_user);
        btn_login=findViewById(R.id.btn_login);
        progressBar=findViewById(R.id.progressLoad);

        mAuth=FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailuser=email.getText().toString().trim();
                String passworduser=password.getText().toString().trim();

                login(emailuser,passworduser);
            }
        });

    }
    private void login(String email, String password)
    {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(login.this,"Successful Login", Toast.LENGTH_SHORT).show();

                            progressBar.setVisibility(View.VISIBLE);

                            Timer timer = new Timer();
                            TimerTask timerTask = new TimerTask() {
                                @Override
                                public void run() {
                                    counter++;

                                    progressBar.setProgress(counter);

                                    if(counter == 100)
                                    {
                                        timer.cancel();

                                        Intent intent=new Intent(login.this,Welcome.class);
                                        intent.putExtra("email",email);
                                        startActivity(intent);
                                    }
                                }
                            };

                            timer.schedule(timerTask, 100, 100);

                        } else {
                            Toast.makeText(login.this, "Invalid Input", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}