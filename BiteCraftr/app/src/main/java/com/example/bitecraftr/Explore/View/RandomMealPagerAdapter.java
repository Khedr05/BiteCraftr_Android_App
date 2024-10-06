package com.example.bitecraftr.Explore.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bitecraftr.MealDetails.View.MealDetailsFragment;
import com.example.bitecraftr.Model.Meal;
import com.example.bitecraftr.NavbarActivity;
import com.example.bitecraftr.R;

import java.util.List;

// Adapter for displaying random meals in a RecyclerView
public class RandomMealPagerAdapter extends RecyclerView.Adapter<RandomMealPagerAdapter.ViewHolder> {

    private final Context context; // Context for accessing application resources
    private List<Meal> meals; // List of meals to be displayed

    // Constructor to initialize context and meal list
    public RandomMealPagerAdapter(Context context, List<Meal> meals) {
        this.context = context;
        this.meals = meals;
    }

    // ViewHolder class to hold the view for each meal item
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView randomMealImg; // ImageView to display the meal thumbnail
        TextView randomMealName; // TextView to display the meal name
        // Constructor to initialize the ImageView
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            randomMealImg = itemView.findViewById(R.id.randomMealImg); // Find ImageView by ID
            randomMealName = itemView.findViewById(R.id.randomMealName); // Find TextView by ID
        }
    }

    // Creates new ViewHolder instances and inflates the item layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the random_meal_container layout for each item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.random_meal_container, parent, false);
        return new ViewHolder(view); // Return a new ViewHolder instance
    }

    // Binds data to the ViewHolder for each meal item
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Load the meal thumbnail image using Glide
        Glide.with(context)
                .load(meals.get(position).getStrMealThumb()) // Get thumbnail URL from Meal object
                .apply(new RequestOptions() // Set placeholder and error images
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground))
                .into(holder.randomMealImg); // Set the loaded image into the ImageView

        // Set an onClickListener for the ImageView to navigate to MealDetailsFragment
        holder.randomMealImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a MealDetailsFragment instance for the selected meal
                MealDetailsFragment allMealDetailsFragment = MealDetailsFragment.getCurrentMeal(meals.get(position));

                // Replace the current fragment in the container with MealDetailsFragment
                ((NavbarActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, allMealDetailsFragment) // Replace fragment
                        .addToBackStack(null) // Add transaction to back stack
                        .commit(); // Commit the transaction
            }
        });

        holder.randomMealName.setText(meals.get(position).getStrMeal()); // Set meal name
    }

    // Returns the total number of items in the list
    @Override
    public int getItemCount() {
        if (meals == null) {
            return 0; // Return 0 if the list is null
        }
        else {
            return meals.size(); // Return the size of the meals list
        }
    }

    // Updates the meal list and notifies the adapter of the change
    public void setList(List<Meal> meals) {
        this.meals = meals; // Update the meals list
    }
}
