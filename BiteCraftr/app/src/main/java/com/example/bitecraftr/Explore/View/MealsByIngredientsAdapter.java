package com.example.bitecraftr.Explore.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bitecraftr.Model.Meal;
import com.example.bitecraftr.R;

import java.util.List;

public class MealsByIngredientsAdapter extends RecyclerView.Adapter<MealsByIngredientsAdapter.ViewHolder> {

    private final Context context; // Context for loading resources
    private List<Meal> allingredientsList; // List of meals
    private MealsByIngredientsAdapterListener listener; // Listener for meal clicks

    // Constructor for initializing the adapter with context, category list, and listener
    public MealsByIngredientsAdapter(Context context, List<Meal> countryList, MealsByIngredientsAdapterListener listener) {
        this.context = context;
        this.allingredientsList = countryList;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mealImg; // ImageView for meal thumbnail
        private TextView meaTtitle; // TextView for meal title

        // ViewHolder constructor
        public ViewHolder(@NonNull View v) {
            super(v);
            mealImg = v.findViewById(R.id.mealByIngImg); // Initialize ImageView
            meaTtitle = v.findViewById(R.id.mealByIngTitle); // Initialize TextView
        }
    }

    @NonNull
    @Override
    public MealsByIngredientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext()); // Create LayoutInflater
        View v = inflater.inflate(R.layout.meals_by_ingredients_row, recyclerView, false); // Inflate the row layout
        return new MealsByIngredientsAdapter.ViewHolder(v); // Return the new ViewHolder
    }

    @Override
    public void onBindViewHolder(@NonNull MealsByIngredientsAdapter.ViewHolder holder, int position) {
        Meal meal = allingredientsList.get(position); // Get the meal at the current position

        // Load the meal image using Glide
        Glide.with(context).load(meal.getStrMealThumb())
                .apply(new RequestOptions().override(200, 200) // Set image size
                        .placeholder(R.drawable.ic_launcher_background) // Placeholder while loading
                        .error(R.drawable.ic_launcher_foreground)) // Image to show on error
                .into(holder.mealImg); // Set the loaded image to ImageView

        holder.meaTtitle.setText(meal.getStrMeal()); // Set meal title in TextView

        // Set click listener for the entire item view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) { // Check if listener is not null
                    listener.onIngredientMealClick(meal.getIdMeal()); // Trigger the listener's click method
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (allingredientsList == null) {
            return 0; // Return 0 if the list is null
        }
        else{
            return allingredientsList.size(); // Return the number of meals
        }
    }

    // Method to update the meal list
    public void setList(List<Meal> ingredientsList) {
        this.allingredientsList = ingredientsList; // Update the list of meals
    }
}
