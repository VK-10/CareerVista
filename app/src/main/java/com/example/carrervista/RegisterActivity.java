package com.example.carrervista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    Button register;
    EditText password;
    EditText email;
    EditText number;
    EditText name;

    private FirebaseAuth mAuth;

    private DatabaseReference mDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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

                registration(user_name,user_email,user_password,user_phone_number);
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
                            Toast.makeText(RegisterActivity.this, "Registraion Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this,String.valueOf(task.getException()) , Toast.LENGTH_SHORT).show();
                            Log.d("Some Error", String.valueOf(task.getException()));
                        }
                    }
                });
    }

    private void addtodata(String name,String email,String uid,String number){
        mDbRef= FirebaseDatabase.getInstance().getReference();
        mDbRef.child("Clients").child(uid).setValue(new Clients(name,email,uid,number));
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