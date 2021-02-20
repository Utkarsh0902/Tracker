    package com.example.basicexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

    public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginClick(View view){
        EditText usernameText = (EditText)findViewById(R.id.username);
        EditText passwordText = (EditText)findViewById(R.id.password);//look for a view and convert into EditText
        String username  = usernameText.getText().toString(); //convert it into a string
        String password  = passwordText.getText().toString();
        String toastText = "Username is: "+ username + "\nPassword is:  "+ password;

        //make a toast and display the username and password
        Toast.makeText(this, toastText,Toast.LENGTH_SHORT).show();

    }
}