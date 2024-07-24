package com.example.axforasset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class ProfileActivity extends AppCompatActivity {
    private CardView menu;
    private FrameLayout hotspot;
    private ImageButton navButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize views
        navButton = findViewById(R.id.navButton);
        menu = findViewById(R.id.menuNav);
        hotspot = findViewById(R.id.hotspot);

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
    }
}
