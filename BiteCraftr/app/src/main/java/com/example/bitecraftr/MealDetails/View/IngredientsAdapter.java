package com.example.bitecraftr.MealDetails.View;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bitecraftr.Model.Ingredients;
import com.example.bitecraftr.R;

import java.util.List;

/**
 * Adapter class for displaying a list of ingredients in a RecyclerView.
 * This class binds ingredient data to the views in each row of the RecyclerView.
 */
public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private static final String TAG = "IngredientsAdapter"; // Tag for logging
    private final Context context; // Context for accessing resources
    private List<Ingredients> ingredientsList; // List of ingredients to display

    /**
     * Constructor for IngredientsAdapter.
     *
     * @param context         The context for accessing resources.
     * @param ingredientsList The list of ingredients to display.
     */
    public IngredientsAdapter(Context context, List<Ingredients> ingredientsList) {
        this.context = context;
        this.ingredientsList = ingredientsList;
    }

    /**
     * ViewHolder class that holds the views for each ingredient item.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ingredientsImg; // ImageView for displaying ingredient image
        private TextView ingredientsTitle; // TextView for displaying ingredient name
        private TextView ingredientsMeasure; // TextView for displaying ingredient measure
        public ConstraintLayout constraintLayout; // ConstraintLayout for layout management
        public View layout; // The entire row layout

        /**
         * Constructor for ViewHolder.
         *
         * @param v The view for this row.
         */
        public ViewHolder(@NonNull View v) {
            super(v);
            layout = v;
            ingredientsImg = v.findViewById(R.id.ingredientsImg);
            ingredientsTitle = v.findViewById(R.id.ingredientsTitle);
            ingredientsMeasure = v.findViewById(R.id.ingredientsMeasure);
            constraintLayout = v.findViewById(R.id.ingredientRow);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        // Inflate the row layout for each item
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.ingredients_details_row, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "===== onCreateViewHolder ====="); // Log for debugging
        return vh; // Return the created ViewHolder
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Load the ingredient image using Glide
        Glide.with(context).load(ingredientsList.get(position).getIngredientsThumbnail())
                .apply(new RequestOptions().override(200, 200) // Set image size
                        .placeholder(R.drawable.ic_launcher_background) // Placeholder image
                        .error(R.drawable.ic_launcher_foreground)) // Error image
                .into(holder.ingredientsImg);

        // Set the ingredient name and measure
        holder.ingredientsTitle.setText(ingredientsList.get(position).getStrIngredient());
        holder.ingredientsMeasure.setText(ingredientsList.get(position).getIngredientsMeasure());
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size(); // Return the total number of ingredients
    }

    /**
     * Update the list of ingredients and refresh the RecyclerView.
     *
     * @param ingredients The new list of ingredients.
     */
    public void setList(List<Ingredients> ingredients) {
        Log.i(TAG, "setList: " + ingredients); // Log for debugging
        this.ingredientsList = ingredients;
        notifyDataSetChanged(); // Notify the adapter to refresh the views
    }
}
