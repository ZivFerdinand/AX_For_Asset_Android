package com.example.axforasset;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    TextInputEditText usernameField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameField = findViewById(R.id.username);
        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(
                v -> {
                    UserData.getInstance().setUsername(usernameField.getText().toString());
                    // Intent untuk berpindah ke LoginActivity
                    Intent intent = new Intent(LoginActivity.this, Home.class);
                    startActivity(intent);
                }
        );
    }

}