package com.example.axforasset;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Get the data passed from the ItemsAdapter
        String itemTitle = getIntent().getStringExtra("ITEM_TITLE");
        String itemDescription = getIntent().getStringExtra("ITEM_DESCRIPTION");
        int itemImageResource = getIntent().getIntExtra("ITEM_IMAGE", -1);

        // Initialize UI elements
        TextView titleTextView = findViewById(R.id.itemTitle);
        TextView descriptionTextView = findViewById(R.id.itemDescription);
        ImageView imageView = findViewById(R.id.itemImageView);
        Spinner paymentMethodsSpinner = findViewById(R.id.paymentMethodsSpinner);

        // Set data to UI elements
        if (itemTitle != null) {
            titleTextView.setText(itemTitle);
        }

        if (itemDescription != null) {
            descriptionTextView.setText(itemDescription);
        }

        if (itemImageResource != -1) {
            imageView.setImageResource(itemImageResource);
        }

        // Set up the payment methods spinner with custom layouts
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.payment_methods, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        paymentMethodsSpinner.setAdapter(adapter);

        // Set up back button
        findViewById(R.id.backButton).setOnClickListener(v -> finish());
    }
}
