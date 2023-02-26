package com.example.carrervista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {

    Button register;
    EditText password;
    EditText email;
    EditText number;
    EditText name;
    String otp;

    private FirebaseAuth mAuth;

    private DatabaseReference mDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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

        register=findViewById(R.id.registerbutton);
        email=findViewById(R.id.email_user);
        number=findViewById(R.id.phone_number);
        password=findViewById(R.id.password_user);
        name=findViewById(R.id.name_user);


        mAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_email=email.getText().toString().trim();
                String user_password=password.getText().toString();
                String user_phone_number=number.getText().toString();
                String user_name=name.getText().toString();

                if(user_phone_number.isEmpty() || user_phone_number.length()!=10)
                {
                    Toast.makeText(RegisterActivity.this, "Please Enter a Valid Number", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    phoneverification(user_phone_number,user_name,user_email,user_password);

                }
            }
        });



    }

    public void phoneverification(String user_phone_number,String user_name,String user_email,String user_password)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + user_phone_number, 60, TimeUnit.SECONDS, RegisterActivity.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        Intent intent=new Intent(RegisterActivity.this, OTPverification.class);
                        intent.putExtra("number",user_phone_number);
                        intent.putExtra("otp",s);
                        intent.putExtra("name",user_name);
                        intent.putExtra("email",user_email);
                        intent.putExtra("password",user_password);
                        startActivity(intent);
                    }
                }
        );
    }


//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            currentUser.reload();
//        }
//    }
}