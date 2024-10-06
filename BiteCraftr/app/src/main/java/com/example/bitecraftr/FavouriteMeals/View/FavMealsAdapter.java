package com.example.bitecraftr.FavouriteMeals.View;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bitecraftr.MealDetails.View.MealDetailsFragment;
import com.example.bitecraftr.Model.Meal;
import com.example.bitecraftr.NavbarActivity;
import com.example.bitecraftr.R;

import java.util.ArrayList;
import java.util.List;

// Adapter class to manage displaying and interacting with favorite meals in a RecyclerView
public class FavMealsAdapter extends RecyclerView.Adapter<FavMealsAdapter.ViewHolder>{

    private final Context context;  // Context of the activity/fragment where the RecyclerView is displayed
    private List<Meal> mealList;    // List of meals to be displayed in the RecyclerView
    private static final String TAG = "FavMealsAdapter";  // Tag used for logging
    private OnFavMealsAdapterListener listener;  // Listener to handle actions like removing a meal

    // Constructor for the adapter, takes in context, list of meals, and a listener for handling actions
    public FavMealsAdapter(Context _context, List<Meal> myDataset, OnFavMealsAdapterListener listener) {
        this.context = _context;
        this.mealList = myDataset != null ? myDataset : new ArrayList<>();  // Initialize meal list or set to empty list if null
        this.listener = listener;
    }

    // ViewHolder class to hold the views for each item in the RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;  // ImageView to display the meal image
        private TextView titleTxtView;  // TextView to display the meal name
        private ImageButton removeBtn;  // Button to remove a meal from the favorites
        private ConstraintLayout constraintLayout;  // Layout for the item
        private View layout;  // The root view of the item

        // Constructor that binds the views from the layout file to the variables
        public ViewHolder(View v) {
            super(v);
            layout = v;
            img = v.findViewById(R.id.favMealImg);
            titleTxtView = v.findViewById(R.id.favMealTitle);
            removeBtn = v.findViewById(R.id.removeBtn);
            constraintLayout = v.findViewById(R.id.main);
        }
    }

    // Creates a new ViewHolder when the RecyclerView needs it
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());  // Inflates the custom layout for each meal row
        View v = inflater.inflate(R.layout.fav_meals_row, recyclerView, false);  // Inflate the item layout
        ViewHolder vh = new ViewHolder(v);  // Create a new ViewHolder
        Log.i(TAG, "===== onCreateViewHolder =====");  // Log when the ViewHolder is created
        return vh;
    }

    // Binds data (meal information) to the views for each item in the RecyclerView
    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Meal meal = mealList.get(position);  // Get the meal for the current position

        // Use Glide to load the meal image into the ImageView
        Glide.with(context).load(meal.getStrMealThumb())
                .apply(new RequestOptions().override(200, 200)
                        .placeholder(R.drawable.ic_launcher_background)  // Show placeholder image if loading fails
                        .error(R.drawable.ic_launcher_foreground))  // Show error image if loading fails
                .into(holder.img);

        // Set up the click listener for the meal image to open the meal details fragment
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MealDetailsFragment allMealDetailsFragment = MealDetailsFragment.getCurrentMeal(meal);  // Pass the selected meal to the details fragment
                ((NavbarActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, allMealDetailsFragment)  // Replace the current fragment with the meal details fragment
                        .addToBackStack(null)  // Add the transaction to the back stack so the user can navigate back
                        .commit();
            }
        });

        // Set the meal title in the TextView
        holder.titleTxtView.setText(meal.getStrMeal());

        // Set up the click listener for the remove button to remove a meal from favorites
        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(context)
                        .setMessage("Are you sure you want to remove this meal from favorites ?")
                        .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> {
                                    listener.onFavRemoveMealClick(meal);  // Notify the listener when the remove button is clicked
                                })
                        .setNegativeButton(android.R.string.no, null).show()
                        .show();
            }
        });

        // Set up the click listener for the whole layout to display a Toast message with the meal name
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, meal.getStrMeal(), Toast.LENGTH_SHORT).show();  // Show the meal name in a Toast
            }
        });

        Log.i(TAG, "***** onBindViewHolder **************");  // Log when data is bound to the view
    }

    // Returns the total number of items in the meal list
    @Override
    public int getItemCount() {
        return mealList.size();
    }

    // Sets the list of meals and notifies the adapter to refresh the data
    public void setMeals(List<Meal> meal) {
        this.mealList = meal != null ? meal : new ArrayList<>();  // If the list is null, set it to an empty list
        notifyDataSetChanged();  // Notify the adapter that the data has changed
    }
}
