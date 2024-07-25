package com.example.axforasset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    TextInputEditText usernameField;
    TextInputEditText passwordField;

    TextView usernameError;
    TextView passwordError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameField = findViewById(R.id.username);
        passwordField = findViewById(R.id.password);
        usernameError = findViewById(R.id.username_error);
        passwordError = findViewById(R.id.password_error);

        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(
                v -> {
                    if (usernameField.getText().toString().isEmpty()){
                        usernameError.setText("Username cannot be empty");
                        usernameError.setVisibility(TextView.VISIBLE);
                        return;
                    }
                    else
                    {
                        usernameError.setVisibility(TextView.GONE);
                    }

                    if (passwordField.getText().toString().isEmpty()){
                        passwordError.setText("Password cannot be empty");
                        passwordError.setVisibility(TextView.VISIBLE);
                        return;
                    }
                    else
                    {
                        passwordError.setVisibility(TextView.GONE);
                    }

                    if (passwordField.getText().toString().length()<8){
                        passwordError.setText("Password must be at least 8 characters");
                        passwordError.setVisibility(TextView.VISIBLE);
                        return;
                    }
                    else
                    {
                        passwordError.setVisibility(TextView.GONE);

                    }

                    UserData.getInstance().setUsername(usernameField.getText().toString());
                    // Intent untuk berpindah ke LoginActivity
                    Intent intent = new Intent(LoginActivity.this, Home.class);
                    startActivity(intent);
                }
        );
    }

}