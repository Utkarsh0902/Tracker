package com.example.tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText emailId;
    EditText password;
    Button signInButton;
    Button signUpButton;
    ImageButton googleSignIn;
    FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signInButton = findViewById(R.id.signin_button);
        signUpButton = findViewById(R.id.signup_button);
        googleSignIn = findViewById(R.id.google_signin);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pass = password.getText().toString();

                if(email.isEmpty()){
                    emailId.setError("Enter your e-mail");
                    emailId.requestFocus();
                }

                else if(pass.isEmpty()){
                    password.setError("Enter your password");
                    password.requestFocus();
                }

                else if(!(email.isEmpty() && pass.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Sign-up unsuccessful",Toast.LENGTH_SHORT);
                            }
                            else{
                                 startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                            }
                        }
                    });

                }

            }
        });
    }
}