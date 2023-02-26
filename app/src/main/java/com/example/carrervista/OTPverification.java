package com.example.carrervista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carrervista.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OTPverification extends AppCompatActivity {

    TextView txtnumber;

    EditText otp1,otp2,otp3,otp4,otp5,otp6;
    Button submit_otp;

    private FirebaseAuth mAuth;
    private DatabaseReference mDbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);

        mAuth = FirebaseAuth.getInstance();

        otp1=findViewById(R.id.otp1);
        otp2=findViewById(R.id.otp2);
        otp3=findViewById(R.id.otp3);
        otp4=findViewById(R.id.otp4);
        otp5=findViewById(R.id.otp5);
        otp6=findViewById(R.id.otp6);

        txtnumber=findViewById(R.id.show_number);

        submit_otp=findViewById(R.id.submit_otp);

        String usernumber=getIntent().getStringExtra("number");
        String otp=getIntent().getStringExtra("otp");
        String name=getIntent().getStringExtra("name");
        String email=getIntent().getStringExtra("email");
        String password=getIntent().getStringExtra("password");




        txtnumber.setText(String.format("+91- %s",usernumber));

        submit_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!otp1.getText().toString().trim().isEmpty()||!otp2.getText().toString().trim().isEmpty()||!otp3.getText().toString().trim().isEmpty()||
                !otp4.getText().toString().trim().isEmpty()||!otp5.getText().toString().trim().isEmpty()||!otp6.getText().toString().trim().isEmpty())
                {
                    String s1=otp1.getText().toString().trim()+otp2.getText().toString().trim()+otp3.getText().toString().trim()+otp4.getText().toString().trim()
                            +otp5.getText().toString().trim()+otp6.getText().toString().trim();


                    if(otp != null)
                    {
                        PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(otp,s1);

                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful())
                                {
                                    registration(name,email,password,usernumber);
                                }
                                else {
                                    Toast.makeText(OTPverification.this,"Not valid Otp",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(OTPverification.this,MainActivity.class);
                                    startActivity(intent);

                                }


                            }
                        });


                    }







                }
                else
                {
                    Toast.makeText(OTPverification.this, "Please enter a valid otp", Toast.LENGTH_SHORT).show();

                }
            }
        });

        numbermove();






    }

    private void numbermove() {

        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!charSequence.toString().trim().isEmpty())
                {
                    otp2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!charSequence.toString().trim().isEmpty())
                {
                    otp3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!charSequence.toString().isEmpty())
                {
                    otp4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!charSequence.toString().trim().isEmpty())
                {
                    otp5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!charSequence.toString().trim().isEmpty())
                {
                    otp6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



    }

    public void registration(String name,String email,String password,String number)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            addtodata(name,email,user.getUid(),number);
                            Toast.makeText(OTPverification.this, "Registraion Successful", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(OTPverification.this, Welcome.class);
                            intent.putExtra("name",name);
                            startActivity(intent);

                        } else {
                            Toast.makeText(OTPverification.this,String.valueOf(task.getException()) , Toast.LENGTH_SHORT).show();
                            Log.d("Some Error", String.valueOf(task.getException()));
                        }
                    }
                });
    }
    private void addtodata(String name,String email,String uid,String number){
        mDbRef= FirebaseDatabase.getInstance().getReference();
        mDbRef.child("Clients").child(uid).setValue(new Clients(name,email,uid,number));
    }
}