package com.example.carrervista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
//                    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//
//                        @Override
//                        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
//                            // This callback will be invoked in two situations:
//                            // 1 - Instant verification. In some cases the phone number can be instantly
//                            //     verified without needing to send or enter a verification code.
//                            // 2 - Auto-retrieval. On some devices Google Play services can automatically
//                            //     detect the incoming verification SMS and perform verification without
//                            //     user action.
//
//                            signInWithPhoneAuthCredential(credential);
//                        }
//
//                        @Override
//                        public void onVerificationFailed(@NonNull FirebaseException e) {
//                            // This callback is invoked in an invalid request for verification is made,
//                            // for instance if the the phone number format is not valid.
//                            Log.w(TAG, "onVerificationFailed", e);
//
//                            if (e instanceof FirebaseAuthInvalidCredentialsException) {
//                                // Invalid request
//                            } else if (e instanceof FirebaseTooManyRequestsException) {
//                                // The SMS quota for the project has been exceeded
//                            }
//
//                            // Show a message and update the UI
//                        }
//
//                        @Override
//                        public void onCodeSent(@NonNull String verificationId,
//                                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
//                            // The SMS verification code has been sent to the provided phone number, we
//                            // now need to ask the user to enter the code and then construct a credential
//                            // by combining the code with a verification ID.
//                            Log.d(TAG, "onCodeSent:" + verificationId);
//
//                            // Save verification ID and resending token so we can use them later
//                            mVerificationId = verificationId;
//                            mResendToken = token;
//                        }
//                    };
//                    PhoneAuthOptions options =
//                            PhoneAuthOptions.newBuilder(mAuth)
//                                    .setPhoneNumber("+91"+user_phone_number)       // Phone number to verify
//                                    .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//                                    .setActivity(RegisterActivity.this)                 // Activity (for callback binding)
//                                    .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
//                                    .build();
//                    PhoneAuthProvider.verifyPhoneNumber(options);



                         phoneverification(user_phone_number);
                        //  registration(user_name,user_email,user_password,user_phone_number);

                }
            }
        });



    }

    public void phoneverification(String user_phone_number)
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
                        startActivity(intent);
                    }
                }
        );
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
//                            Toast.makeText(RegisterActivity.this, "Registraion Successful", Toast.LENGTH_SHORT).show();
//                            Intent intent=new Intent(RegisterActivity.this, OTPverification.class);
//                            intent.putExtra("number",number);
//                            startActivity(intent);

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