package com.example.axforasset;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.graphics.Typeface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    ViewPager2 viewPager2;
    TabLayout tabLayout;
    ViewPager viewPagerTabs;
    TabPagerAdapter tabPagerAdapter;
//    Button profileButton;
    private CardView menu;
    private FrameLayout hotspot;
    private ImageButton navButton;
    private Button itemsButton;
    private Button profileButton;
    private Button logoutButton;
    private Handler slideHandler = new Handler();
    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        username = findViewById(R.id.name);
        username.setText(UserData.getInstance().getUsername());
        viewPager2 = findViewById(R.id.viewPager);
        navButton = findViewById(R.id.navButton);
        menu = findViewById(R.id.menuNav);
        hotspot = findViewById(R.id.hotspot);
        itemsButton=findViewById(R.id.itemsButton);
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
        itemsButton.setOnClickListener(
                v->{
                    Intent intent = new Intent(Home.this, ItemsActivity.class);
                    startActivity(intent);
                }
        );
        profileButton.setOnClickListener(
                v->{
                    Intent intent = new Intent(Home.this, ProfileActivity.class);
                    startActivity(intent);
                }
        );
        logoutButton.setOnClickListener(
                v->{
                    Intent intent = new Intent(Home.this, LoginActivity.class);
                    startActivity(intent);
                }
        );
        List<SlideItem> sliderItem = new ArrayList<>();
        sliderItem.add(new SlideItem(R.drawable.carousel_1));
        sliderItem.add(new SlideItem(R.drawable.carousel_2));
        sliderItem.add(new SlideItem(R.drawable.carousel_3));
        sliderItem.add(new SlideItem(R.drawable.carousel_4));

        viewPager2.setAdapter(new SlideAdapter(sliderItem, viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(5);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(30));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1-Math.abs(position);
                page.setScaleY(0.85f + r*0.15f  );
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                slideHandler.removeCallbacks(sliderRunnable);
                slideHandler.postDelayed(sliderRunnable, 3000);
            }
        });

//        tab
        tabLayout = findViewById(R.id.tabLayout);
        viewPagerTabs = findViewById(R.id.viewPagerTabs);
        tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerTabs.setAdapter(tabPagerAdapter);
        tabLayout.setupWithViewPager(viewPagerTabs);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                // Inflate a simple TextView as custom view for each tab
                TextView tabTextView = (TextView) LayoutInflater.from(this).inflate(android.R.layout.simple_list_item_1, null);
                tabTextView.setText(tab.getText()); // Set the tab's text
                tabTextView.setTextSize(16); // Set text size
                tabTextView.setTypeface(null, Typeface.BOLD); // Set text style
                tabTextView.setTextColor(Color.parseColor("#000000")); // Set text color
                tabTextView.setGravity(Gravity.CENTER);
                tab.setCustomView(tabTextView);
            }
        }
//        profileButton=findViewById(R.id.profilebtn);
//        profileButton.setOnClickListener(
//                v -> {
//                    // Intent untuk berpindah ke LoginActivity
//                    Intent intent = new Intent(Home.this, ProfileActivity.class);
//                    startActivity(intent);
//                }
//        );
    }


    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        slideHandler.postDelayed(sliderRunnable, 3000);
    }
}