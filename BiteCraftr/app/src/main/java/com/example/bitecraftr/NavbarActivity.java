package com.example.bitecraftr;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.bitecraftr.Explore.View.ExploreFragment;
import com.example.bitecraftr.FavouriteMeals.View.FavoritesFragment;
import com.example.bitecraftr.ScheduleMeals.View.ScheduleFragment;
import com.example.bitecraftr.Search.View.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display, allowing the UI to extend into system windows (status bar, navigation bar, etc.)
        EdgeToEdge.enable(this);

        // Set the layout for this activity to 'activity_navbar.xml', which contains the BottomNavigationView and fragment container
        setContentView(R.layout.activity_navbar);

        // Find the BottomNavigationView by its ID defined in the layout to allow interaction
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set up a listener to handle item selection events from the bottom navigation menu
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Initialize a fragment variable to store the selected fragment based on user's choice
                Fragment selectedFragment = null;

                // Get the ID of the selected menu item
                int itemId = item.getItemId();

                // Determine which fragment to load based on the selected menu item's ID
                if (itemId == R.id.Explore) {
                    // User selected the 'Explore' tab, load ExploreFragment
                    selectedFragment = new ExploreFragment();
                } else if (itemId == R.id.Favorites) {
                    // User selected the 'Favorites' tab, load FavoritesFragment
                    selectedFragment = new FavoritesFragment();
                } else if (itemId == R.id.Schedule) {
                    // User selected the 'Schedule' tab, load ScheduleFragment
                    selectedFragment = new ScheduleFragment();
                } else if (itemId == R.id.Search) {
                    // User selected the 'Search' tab, load SearchFragment
                    selectedFragment = new SearchFragment();
                }

                // If a valid fragment is selected (i.e., not null), perform the fragment transaction to replace the current fragment
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer, selectedFragment) // Replace current fragment
                            .addToBackStack(null) // Add transaction to back stack for navigation
                            .commit(); // Commit the transaction
                }

                return true; // Return true to indicate the item selection has been handled
            }
        });

        // If this is the first time the activity is being created (not after a configuration change),
        // set the default fragment to be ExploreFragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new ExploreFragment()) // Set ExploreFragment as the default
                    .commit(); // Commit the initial fragment transaction
        }
    }
}
