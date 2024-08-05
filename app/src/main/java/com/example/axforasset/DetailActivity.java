package com.example.axforasset;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class DetailActivity extends AppCompatActivity {

    private EditText emailEditText;
    private Spinner paymentMethodsSpinner;
    private Button purchaseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Get the data passed from the ItemsAdapter
        String itemTitle = getIntent().getStringExtra("ITEM_TITLE");
        String itemDescription = getIntent().getStringExtra("ITEM_DESCRIPTION");
        int itemPrice = getIntent().getIntExtra("ITEM_PRICE", -1); // Receive the price
        int itemImageResource = getIntent().getIntExtra("ITEM_IMAGE", -1);

        // Initialize UI elements
        TextView titleTextView = findViewById(R.id.itemTitle);
        TextView descriptionTextView = findViewById(R.id.itemDescription);
        TextView priceTextView = findViewById(R.id.itemPrice); // Add this line
        ImageView imageView = findViewById(R.id.itemImageView);
        paymentMethodsSpinner = findViewById(R.id.paymentMethodsSpinner);
        emailEditText = findViewById(R.id.emailEditText);
        purchaseButton = findViewById(R.id.purchaseButton);

        // Set data to UI elements
        if (itemTitle != null) {
            titleTextView.setText(itemTitle);
        }

        if (itemDescription != null) {
            descriptionTextView.setText(itemDescription);
        }

        if (itemPrice != -1) { // Add this block
            priceTextView.setText("IDR " + itemPrice);
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

        // Set up purchase button with validation and interaction
        purchaseButton.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    return true;
                case MotionEvent.ACTION_UP:
                    v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    validateAndSubmit();
                    return true;
                default:
                    return false;
            }
        });
    }

    private void validateAndSubmit() {
        String email = emailEditText.getText().toString();
        String paymentMethod = paymentMethodsSpinner.getSelectedItem().toString();

        if (paymentMethod.equals("--Choose Payment--")) {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Error")
                    .setMessage("Please select a valid payment method")
                    .show();
        } else if (email.isEmpty()) {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Error")
                    .setMessage("Email is required")
                    .show();
        } else if (!email.contains("@gmail.com")) {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Error")
                    .setMessage("Email doesn't have @gmail.com")
                    .show();
        } else {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Success")
                    .setMessage("A confirmation email has been sent to your email.")
                    .setCancelable(false)
                    .show();

            // Automatically dismiss dialog and redirect after 3 seconds
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                redirectToItemsPage();
            }, 3000);
        }
    }

    private void redirectToItemsPage() {
        Intent intent = new Intent(DetailActivity.this, ItemsActivity.class);
        startActivity(intent);
        finish();
    }
}
