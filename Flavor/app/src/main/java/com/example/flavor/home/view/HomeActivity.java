package com.example.flavor.home.view;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.flavor.R;
import com.example.flavor.favFragment;
import com.example.flavor.homeFragment;
import com.example.flavor.scheduleFragment;
import com.example.flavor.searchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        BottomNavigationView btnNavView = findViewById(R.id.bottom_navigation);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new homeFragment()).commit();
        }
        btnNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment currentFragment = null;
                int itemId = item.getItemId();
                if (itemId==R.id.homePage)
                {
                    currentFragment=new homeFragment();
                }
                else if (itemId==R.id.searchPage){
                    currentFragment=new searchFragment();
                }
                else if (itemId==R.id.favPage){
                    currentFragment=new favFragment();
                }
                else if (itemId==R.id.schedulePage){
                    currentFragment=new scheduleFragment();
                }

                if (currentFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, currentFragment).commit();
                }
                return true;
            }
        });

    }
}