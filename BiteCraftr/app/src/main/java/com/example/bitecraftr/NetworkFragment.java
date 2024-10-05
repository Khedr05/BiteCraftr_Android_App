package com.example.bitecraftr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.fragment.app.Fragment;

public class NetworkFragment extends Fragment {

    private Animation animation;
    private View animatedView;  // Store the animated view to stop its animation if needed

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_network, container, false);

        // Find the view to animate
        animatedView = view.findViewById(R.id.lottieAnimationViewNetwork); // Assuming this is your animated view

        // Load the animation
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.animation_layout);

        // Check if animation is already running and stop it before starting a new one
        if (animatedView.getAnimation() != null && animatedView.getAnimation().hasStarted() && !animatedView.getAnimation().hasEnded()) {
            // Stop the current animation
            animatedView.clearAnimation();
        }

        // Start the new animation
        animatedView.startAnimation(animation);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Clear the animation when the fragment view is destroyed to avoid memory leaks
        if (animatedView != null) {
            animatedView.clearAnimation();
        }
    }
}
