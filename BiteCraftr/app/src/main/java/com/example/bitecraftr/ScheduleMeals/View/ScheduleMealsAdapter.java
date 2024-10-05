package com.example.bitecraftr.ScheduleMeals.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bitecraftr.MealDetails.View.MealDetailsFragment;
import com.example.bitecraftr.Model.Meal;
import com.example.bitecraftr.Model.ScheduledMeal;
import com.example.bitecraftr.NavbarActivity;
import com.example.bitecraftr.R;

import java.util.List;

/**
 * ScheduleMealsAdapter is a RecyclerView adapter that displays a list of scheduled meals.
 * It allows users to view meal details and remove scheduled meals.
 */
public class ScheduleMealsAdapter extends RecyclerView.Adapter<ScheduleMealsAdapter.ViewHolder> {

    private final Context context; // Context for accessing resources and inflating views
    private List<ScheduledMeal> scheduledMealList; // List of scheduled meals to display
    private final OnScheduleMealsAdapterListener scheduleMealsAdapterListener; // Listener for meal removal events

    // Constructor for initializing the adapter with context, meal list, and listener
    public ScheduleMealsAdapter(Context context, List<ScheduledMeal> values, OnScheduleMealsAdapterListener scheduleMealsAdapterListener) {
        this.context = context;
        this.scheduledMealList = values;
        this.scheduleMealsAdapterListener = scheduleMealsAdapterListener;
    }

    // ViewHolder class to hold views for each scheduled meal item
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView scheduleMealImg; // Image view for displaying the meal image
        private TextView scheduleMealTitle; // Text view for displaying the meal title
        private ImageButton scheduleRemoveBtn; // Button for removing the scheduled meal
        private View layout; // The entire layout for the item

        public ViewHolder(@NonNull View v) {
            super(v);
            // Initialize views by finding them by their IDs
            scheduleMealTitle = v.findViewById(R.id.scheduleMealTitle);
            scheduleMealImg = v.findViewById(R.id.scheduleMealImg);
            scheduleRemoveBtn = v.findViewById(R.id.removeBtn);
            layout = v; // Set the layout
        }
    }

    @NonNull
    @Override
    public ScheduleMealsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        // Inflate the item layout for each scheduled meal
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.schedule_meal_row, recyclerView, false);
        return new ViewHolder(v); // Return a new ViewHolder with the inflated view
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleMealsAdapter.ViewHolder holder, int position) {
        // Get the scheduled meal for the current position
        ScheduledMeal scheduledMeal = scheduledMealList.get(position);
        Meal meal = scheduledMeal.getMeal(); // Get the Meal object from ScheduledMeal

        // Load the meal image using Glide for efficient image loading
        Glide.with(context).load(meal.getStrMealThumb())
                .apply(new RequestOptions().override(200, 200) // Set image size
                        .placeholder(R.drawable.ic_launcher_foreground) // Placeholder image
                        .error(R.drawable.ic_launcher_foreground)) // Error image
                .into(holder.scheduleMealImg); // Set the loaded image into the ImageView

        holder.scheduleMealTitle.setText(meal.getStrMeal()); // Set the meal title in the TextView

        // Set click listener for the layout to navigate to meal details
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MealDetailsFragment allMealDetailsFragment = MealDetailsFragment.getCurrentMeal(meal);

                // Replace the current fragment with the MealDetailsFragment
                ((NavbarActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, allMealDetailsFragment) // Replace fragment
                        .addToBackStack(null).commit(); // Add to back stack for navigation
            }
        });

        // Set click listener for the remove button to trigger meal removal
        holder.scheduleRemoveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheduleMealsAdapterListener.onRemoveMealClick(scheduledMeal); // Notify the listener
            }
        });
    }

    @Override
    public int getItemCount() {
        return scheduledMealList.size(); // Return the total number of scheduled meals
    }

    // Method to update the list of scheduled meals
    public void setList(List<ScheduledMeal> updatedProducts) {
        this.scheduledMealList = updatedProducts; // Update the meal list
    }
}
