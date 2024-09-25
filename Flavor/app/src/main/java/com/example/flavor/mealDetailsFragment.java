package com.example.flavor;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.flavor.model.Meal;

public class MealDetailsFragment extends Fragment {

    private static final String MEAL_KEY = "MEAL_KEY";
    private static final String YOUTUBE_PREFIX = "https://www.youtube.com/";

    private Meal meal;

    private ImageView mealImage;
    private TextView mealTitle;
    private TextView mealDesc;
    private WebView mealVideo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            meal = (Meal) getArguments().getSerializable(MEAL_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_details, container, false);

        mealImage = view.findViewById(R.id.mealImage);
        mealTitle = view.findViewById(R.id.mealTitle);
        mealDesc = view.findViewById(R.id.mealDesc);
        mealVideo = view.findViewById(R.id.mealVideo);

        setupWebView();
        loadImage();
        setMealDetails();

        return view;
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView() {
        WebSettings webSettings = mealVideo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mealVideo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return !(url.startsWith(YOUTUBE_PREFIX + "watch") || url.startsWith(YOUTUBE_PREFIX + "embed/"));
            }
        });

        String youtubeEmbedUrl = "https://www.youtube.com/embed/" + getYoutubeVideoId(meal.getStrYoutube());
        mealVideo.loadUrl(youtubeEmbedUrl);
    }

    private void loadImage() {
        Glide.with(this)
                .load(meal.getStrMealThumb())
                .apply(new RequestOptions()
                        .override(200, 200)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground))
                .into(mealImage);
    }

    private void setMealDetails() {
        mealTitle.setText(meal.getStrMeal());
        mealDesc.setText(meal.getStrInstructions());
    }

    public static MealDetailsFragment getCurrentMeal(Meal meal) {
        MealDetailsFragment fragment = new MealDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(MEAL_KEY, meal);
        fragment.setArguments(args);
        return fragment;
    }

    private String getYoutubeVideoId(String url) {
        if (TextUtils.isEmpty(url)) return "";

        Uri uri = Uri.parse(url);
        String videoId = uri.getQueryParameter("v");

        if (videoId == null) {
            videoId = uri.getLastPathSegment();
        }
        return videoId != null ? videoId : "";
    }
}