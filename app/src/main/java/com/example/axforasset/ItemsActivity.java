package com.example.axforasset;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ItemsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemsAdapter itemsAdapter;
    private List<Item> itemList;
    private List<Item> filteredItemList;
    private EditText searchEditText;
    private Button searchButton;
    private CardView menu;
    private FrameLayout hotspot;
    private ImageButton navButton;
    private Button homeButton;
    private Button profileButton;
    private Button logoutButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        navButton = findViewById(R.id.navButton);
        menu = findViewById(R.id.menuNav);
        hotspot = findViewById(R.id.hotspot);
        homeButton=findViewById(R.id.homeButton);
        profileButton=findViewById(R.id.profileButton);
        logoutButton=findViewById(R.id.logoutButton);
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
                    Intent intent = new Intent(ItemsActivity.this, Home.class);
                    startActivity(intent);
                }
        );
        profileButton.setOnClickListener(
                v->{
                    Intent intent = new Intent(ItemsActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
        );
        logoutButton.setOnClickListener(
                v->{
                    Intent intent = new Intent(ItemsActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
        );
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Initialize the item list and adapter
        itemList = new ArrayList<>();
        // Add your items here
        itemList.add(new Item("Banyan Tree", "A Heavy Branched Tree with Lots of Leaves", R.drawable.banyantree));
        itemList.add(new Item("Sahara Desert", "A One Toned Sahara Desert", R.drawable.saharadesert));
        itemList.add(new Item("House", "Wood Made House", R.drawable.house));
        itemList.add(new Item("Ice Sparks", "Frozen Zoomed Ice", R.drawable.icesparks));
        itemList.add(new Item("Wizard", "Cute Purple Toned Wizard", R.drawable.wizard));
        itemList.add(new Item("Rock", "Greyish Rock, Three Types Included", R.drawable.rock));
        itemList.add(new Item("Kodok Ijo", "Green Regular Frog", R.drawable.kodokijo));
        itemList.add(new Item("Fountain Cat", "A Rare and Limited Supplied Cat Type", R.drawable.fountaincat));
        itemList.add(new Item("Lilac", "An IU Inspired Lilac Album Asset", R.drawable.lilac));
        itemList.add(new Item("Dys Butterfly", "Pretty Butterfly, Four Types Included", R.drawable.dysbutterfly));

        filteredItemList = new ArrayList<>(itemList);

        itemsAdapter = new ItemsAdapter(this, filteredItemList);
        recyclerView.setAdapter(itemsAdapter);

        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(v -> {
            String query = searchEditText.getText().toString().trim();
            filterItems(query);
        });
    }

    private void filterItems(String query) {
        filteredItemList.clear();
        if (query.isEmpty()) {
            filteredItemList.addAll(itemList);
        } else {
            for (Item item : itemList) {
                if (item.getTitle().toLowerCase().contains(query.toLowerCase())) {
                    filteredItemList.add(item);
                }
            }
        }
        itemsAdapter.notifyDataSetChanged();
    }
}
