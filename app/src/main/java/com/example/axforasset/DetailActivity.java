package com.example.axforasset;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView detailImage = findViewById(R.id.detailImage);
        TextView detailTitle = findViewById(R.id.detailTitle);
        TextView detailDescription = findViewById(R.id.detailDescription);

        // Get the passed item
        Item item = (Item) getIntent().getSerializableExtra("item");

        // Set the item details
        detailImage.setImageResource(item.getImageResourceId());
        detailTitle.setText(item.getTitle());
        detailDescription.setText(item.getDescription());
    }
}
