package com.example.bitecraftr.MealDetails.View;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bitecraftr.Database.LocalDataSourceImpl;
import com.example.bitecraftr.MealDetails.Presenter.MealDetailsPresenter;
import com.example.bitecraftr.MealDetails.Presenter.MealDetailsPresenterImpl;
import com.example.bitecraftr.Model.AppRepository;
import com.example.bitecraftr.Model.AppRepositoryImpl;
import com.example.bitecraftr.Model.Ingredients;
import com.example.bitecraftr.Model.Meal;
import com.example.bitecraftr.Model.ScheduledMeal;
import com.example.bitecraftr.Network.RemoteDataSourceImpl;
import com.example.bitecraftr.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment that displays the details of a selected meal,
 * including its image, description, video, and ingredients.
 */
public class MealDetailsFragment extends Fragment implements MealDetailsView {

    private static final String MEAL_KEY = "MEAL_KEY"; // Key for meal argument
    private static final String YOUTUBE_PREFIX = "https://www.youtube.com/"; // YouTube URL prefix

    private Meal meal; // The selected meal
    private ScheduledMeal scheduledMeal; // Meal scheduled for a specific date
    private ImageView mealImage; // ImageView for displaying the meal image
    private TextView mealTitle; // TextView for displaying the meal title
    private TextView mealDesc; // TextView for displaying the meal description
    private WebView mealVideo; // WebView for displaying the meal video
    private Button addBtn; // Button to add meal to favorites
    private Button planBtn; // Button to plan the meal
    private RecyclerView ingredientsRecyclerView; // RecyclerView for displaying ingredients
    private IngredientsAdapter ingredientsAdapter; // Adapter for ingredients RecyclerView
    private MealDetailsPresenter presenter; // Presenter for handling meal details logic

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the meal object from arguments
        if (getArguments() != null) {
            meal = (Meal) getArguments().getSerializable(MEAL_KEY);
            // Initialize the presenter with the repository
            AppRepository repository = AppRepositoryImpl.getInstance(
                    RemoteDataSourceImpl.getInstance(),
                    LocalDataSourceImpl.getInstance(getContext()));
            presenter = new MealDetailsPresenterImpl(this, repository);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_meal_details, container, false);

        // Initialize views
        mealImage = view.findViewById(R.id.mealImage);
        mealTitle = view.findViewById(R.id.mealTitle);
        mealDesc = view.findViewById(R.id.mealDesc);
        mealVideo = view.findViewById(R.id.mealVideo);
        addBtn = view.findViewById(R.id.addToFavouriteButton);
        planBtn = view.findViewById(R.id.addToPlanButton);
        ingredientsRecyclerView = view.findViewById(R.id.ingredientsRecyclerView);

        setupWebView(); // Setup WebView for the meal video

        // Initialize ingredients adapter
        ingredientsAdapter = new IngredientsAdapter(getActivity(), new ArrayList<>());
        ingredientsRecyclerView.setHasFixedSize(true);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        ingredientsRecyclerView.setAdapter(ingredientsAdapter);

        // Set click listener for add to favorite button
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addToFavorite(meal);
            }
        });

        // Set click listener for plan button
        planBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                CleanderFragment dialogFragment = new CleanderFragment();
                dialogFragment.setOnDateSelectedListener(new OnDateSelectedListener() {
                    @Override
                    public void onDateSelected(String selectedDate) {
                        Toast.makeText(getContext(), "Selected date: " + selectedDate, Toast.LENGTH_SHORT).show();
                        scheduledMeal = new ScheduledMeal(meal, selectedDate);
                        presenter.addToPlan(scheduledMeal);
                    }
                });
                dialogFragment.show(getParentFragmentManager(), "calendarDialog");
            }
        });

        // Load meal details after the view has been created
        presenter.loadMealDetails(meal);

        return view; // Return the inflated view
    }

    /**
     * Set up the WebView for displaying the meal video.
     */
    private void setupWebView() {
        WebSettings webSettings = mealVideo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mealVideo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // Allow only specific YouTube URLs to open in WebView
                return !(url.startsWith(YOUTUBE_PREFIX + "watch") || url.startsWith(YOUTUBE_PREFIX + "embed/"));
            }
        });

        // Load the YouTube video using its embed URL
        String youtubeEmbedUrl = "https://www.youtube.com/embed/" + getYoutubeVideoId(meal.getStrYoutube());
        mealVideo.loadUrl(youtubeEmbedUrl);
    }

    @Override
    public void displayMealDetails(Meal meal) {
        // Display the meal details on the UI
        mealTitle.setText(meal.getStrMeal());
        mealDesc.setText(meal.getStrInstructions());
        loadImage(); // Load the meal image
    }

    @Override
    public void displayIngredients(List<Ingredients> ingredients) {
        // Update the ingredients adapter with the list of ingredients
        ingredientsAdapter.setList(ingredients);
        ingredientsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String message) {
        // Show a toast message
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Load the meal image using Glide.
     */
    private void loadImage() {
        Glide.with(this)
                .load(meal.getStrMealThumb())
                .apply(new RequestOptions()
                        .override(200, 200) // Set image size
                        .placeholder(R.drawable.ic_launcher_background) // Placeholder image
                        .error(R.drawable.ic_launcher_foreground)) // Error image
                .into(mealImage); // Load the image into the ImageView
    }

    /**
     * Factory method to create a new instance of MealDetailsFragment with the specified meal.
     *
     * @param meal The meal to display.
     * @return A new instance of MealDetailsFragment.
     */
    public static MealDetailsFragment getCurrentMeal(Meal meal) {
        MealDetailsFragment fragment = new MealDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(MEAL_KEY, meal); // Pass the meal as an argument
        fragment.setArguments(args);
        return fragment; // Return the new fragment instance
    }

    /**
     * Extract the YouTube video ID from the given URL.
     *
     * @param url The YouTube URL.
     * @return The extracted video ID, or an empty string if not found.
     */
    private String getYoutubeVideoId(String url) {
        if (TextUtils.isEmpty(url)) return ""; // Return empty if URL is null or empty

        Uri uri = Uri.parse(url);
        String videoId = uri.getQueryParameter("v");

        // Get video ID from the last path segment if not found in query
        if (videoId == null) {
            videoId = uri.getLastPathSegment();
        }
        return videoId != null ? videoId : ""; // Return video ID or empty string
    }
}
