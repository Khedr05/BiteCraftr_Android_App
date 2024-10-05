package com.example.bitecraftr;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Animation animation;
    TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        animation = AnimationUtils.loadAnimation(this,R.anim.animation_layout);
        txtView =findViewById(R.id.txtViewAppName);
        txtView.startAnimation(animation);
        txtView.postOnAnimationDelayed(new Runnable() {
            @Override
            public void run() {
                Intent outIntent = new Intent(MainActivity.this, NavbarActivity.class);
                startActivity(outIntent);
                finish();
            }},3000);

    }
}