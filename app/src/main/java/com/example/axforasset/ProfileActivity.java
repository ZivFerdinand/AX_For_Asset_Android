package com.example.axforasset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    private CardView menu;
    private FrameLayout hotspot;
    private ImageButton navButton;
    private Button homeButton;
    private Button itemsButton;
    private Button profileButton;
    private Button logoutButton;
    TextView welcomeText;
    TextView emailAddress;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        welcomeText = findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome, "+UserData.getInstance().getUsername()+"!");
        emailAddress=findViewById(R.id.emailAddress);
        emailAddress.setText(UserData.getInstance().getUsername()+"@gmail.com");
        // Initialize views
        navButton = findViewById(R.id.navButton);
        menu = findViewById(R.id.menuNav);
        hotspot = findViewById(R.id.hotspot);
        homeButton=findViewById(R.id.homeButton);
        itemsButton=findViewById(R.id.itemsButton);
        profileButton=findViewById(R.id.profileButton);
        logoutButton=findViewById(R.id.logoutButton);
        // Set up click listener for navButton
        navButton.setOnClickListener(
                v -> {
                    menu.setVisibility(View.VISIBLE);
                    hotspot.setVisibility(View.VISIBLE);
                }
        );

        // Set up click listener for menu
        hotspot.setOnClickListener(
                v -> {
                    menu.setVisibility(View.GONE);
                    hotspot.setVisibility(View.GONE);
                }
        );
        homeButton.setOnClickListener(
                v->{
                    Intent intent = new Intent(ProfileActivity.this, Home.class);
                    startActivity(intent);
                }
        );
        itemsButton.setOnClickListener(
                v->{
                    Intent intent = new Intent(ProfileActivity.this, ItemsActivity.class);
                    startActivity(intent);
                }
        );
        profileButton.setOnClickListener(
                v->{
                    Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
        );
        logoutButton.setOnClickListener(
                v->{
                    Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
        );
    }
}
