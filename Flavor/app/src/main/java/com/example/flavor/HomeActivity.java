package com.example.flavor;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.flavor.favMeals.view.FavMealsFragment;
import com.example.flavor.home.view.homeFragment;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout exploreNav;
    private LinearLayout searchNav;
    private LinearLayout favNav;
    private LinearLayout scheduleNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new homeFragment())
                    .commit();
        }

        exploreNav = findViewById(R.id.linear_explore);
        searchNav = findViewById(R.id.linear_search);
        favNav = findViewById(R.id.linear_fav);
        scheduleNav = findViewById(R.id.linear_schedule);

        exploreNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(new homeFragment());
            }
        });

        searchNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(new searchFragment());
            }
        });

        favNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(new FavMealsFragment());
            }
        });

        scheduleNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(new scheduleFragment());
            }
        });
    }

    private void switchFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
