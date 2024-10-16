package com.example.bitecraftr.MealDetails.View;

import android.content.Intent;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
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
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
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
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
    private ImageView addBtn; // Button to add meal to favorites
    private ImageView planBtn; // Button to plan the meal
    private ImageView backBtn; // Button to go back
    private RecyclerView ingredientsRecyclerView; // RecyclerView for displaying ingredients
    private IngredientsAdapter ingredientsAdapter; // Adapter for ingredients RecyclerView
    private MealDetailsPresenter presenter; // Presenter for handling meal details logic
    private TextView mealArea; // TextView for displaying the meal area
    private ImageView mealAreaFlag; // ImageView for displaying the meal area flag

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
        mealDesc = view.findViewById(R.id.descriptionTxtView);
        mealVideo = view.findViewById(R.id.mealVideo);
        addBtn = view.findViewById(R.id.addToFavBtn);
        planBtn = view.findViewById(R.id.addToScheduleBtn);
        backBtn = view.findViewById(R.id.backBtn);
        ingredientsRecyclerView = view.findViewById(R.id.ingredientsRecView);
        mealArea = view.findViewById(R.id.mealArea);
        mealAreaFlag = view.findViewById(R.id.mealAreaFlag);

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
                CalendarFragment dialogFragment = new CalendarFragment();
                dialogFragment.setOnDateSelectedListener(new OnDateSelectedListener() {
                    @Override
                    public void onDateSelected(String selectedDate) {
                        //Toast.makeText(getContext(), "Selected date: " + selectedDate, Toast.LENGTH_SHORT).show();
                        scheduledMeal = new ScheduledMeal(meal, selectedDate);
                        presenter.addToPlan(scheduledMeal);
                        saveMealToCalendar(meal.getStrMeal(),stringToDate(selectedDate),meal.getStrInstructions());
                    }
                });
                dialogFragment.show(getParentFragmentManager(), "calendarDialog");
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Use FragmentManager to go back to the previous fragment
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.popBackStack(); // Pops the last fragment from the back stack
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
        mealArea.setText(meal.getStrArea());
        loadImage(); // Load the meal image
    }

    @Override
    public void displayIngredients(List<Ingredients> ingredients) {
        // Update the ingredients adapter with the list of ingredients
        ingredientsAdapter.setList(ingredients);
        ingredientsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showSnakebar(String message) {
        // Show a Snakebar message
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * Load the meal image using Glide.
     */
    private void loadImage() {
        Glide.with(this)
                .load(meal.getStrMealThumb())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_launcher_background) // Placeholder image
                        .error(R.drawable.ic_launcher_foreground)) // Error image
                .into(mealImage); // Load the image into the ImageView

        // Load the meal area flag image
        Integer imageId = getContext().getResources().getIdentifier(meal.getStrArea().toLowerCase(), "drawable", getContext().getPackageName());
        mealAreaFlag.setImageResource(imageId);
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

    private Date stringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy"); // Define the expected date format
        Date date = null;
        try {
            date = dateFormat.parse(dateString); // Attempt to parse the string into a Date object
        } catch (ParseException e) {
            e.printStackTrace(); // Print stack trace for debugging
        } catch (java.text.ParseException e) {
            throw new RuntimeException(e);
        }
        return date; // Return the Date object (or null if parsing failed)
    }


    private void saveMealToCalendar(String mealTitle, Date selectedDate, String description) {
        // Use Calendar to manipulate the start and end times
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectedDate);

        // Set start time to the beginning of the selected day (midnight)
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        long startTime = calendar.getTimeInMillis();

        // Set end time to the end of the selected day (one millisecond before midnight)
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        long endTime = calendar.getTimeInMillis();

        // Create the intent to insert the event
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, mealTitle)
                .putExtra(CalendarContract.Events.DESCRIPTION, description)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime) // Event ends on the same day
                .putExtra(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());

        // Check if any app can handle the intent and launch it
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), "No calendar app available", Toast.LENGTH_SHORT).show();
        }
    }

}
