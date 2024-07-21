package com.example.axforasset;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Login {

    private EditText usernameField;
    private EditText passwordField;
    private TextView usernameError;
    private TextView passwordError;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameField = findViewById(R.id.username);
        passwordField = findViewById(R.id.password);
        usernameError = findViewById(R.id.username_error);
        passwordError = findViewById(R.id.password_error);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateFields();
            }
        });
    }

    private void validateFields() {
        boolean isValid = true;

        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        if (username.isEmpty()) {
            usernameError.setText("Username is required");
            usernameError.setVisibility(View.VISIBLE);
            isValid = false;
        } else {
            usernameError.setVisibility(View.GONE);
        }

        if (password.isEmpty()) {
            passwordError.setText("Password is required");
            passwordError.setVisibility(View.VISIBLE);
            isValid = false;
        } else {
            passwordError.setVisibility(View.GONE);
        }

        if (isValid) {
            // Proceed with login logic
        }
    }
}
