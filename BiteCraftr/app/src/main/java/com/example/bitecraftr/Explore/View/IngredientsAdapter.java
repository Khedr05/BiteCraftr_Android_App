package com.example.bitecraftr.Explore.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bitecraftr.Model.Ingredients;
import com.example.bitecraftr.R;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private final Context context;  // Context for inflating views
    private List<Ingredients> ingredientsList;  // List of categories to display
    private IngredientsAdapterListener listener;  // Listener for category clicks

    // Constructor to initialize context, category list, and listener
    public IngredientsAdapter(Context context, List<Ingredients> countries, IngredientsAdapterListener listener) {
        this.context = context;
        this.ingredientsList = countries;
        this.listener = listener;
    }

    // ViewHolder class to hold the views for each item in the RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;  // ImageView for the category image
        private TextView txtView;  // TextView for the category title
        public ConstraintLayout constraintLayout;  // Layout for the category item

        // Constructor to initialize views
        public ViewHolder(View v) {
            super(v);
            img = v.findViewById(R.id.ingredientsImage);  // Initialize the ImageView
            txtView = v.findViewById(R.id.ingredientsTitle);  // Initialize the TextView
            constraintLayout = v.findViewById(R.id.main);  // Initialize the ConstraintLayout
        }
    }

    @NonNull
    @Override
    public IngredientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        // Inflate the layout for each category item
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.explore_ingredients_row, recyclerView, false);
        return new IngredientsAdapter.ViewHolder(v);  // Return a new ViewHolder instance
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.ViewHolder holder, int position) {
        // Get the current ingredient based on its position
        Ingredients ingredients = ingredientsList.get(position);

        // Use Glide to load the image from the given URL
        String imageUrl = "https://www.themealdb.com/images/ingredients/" + ingredients.getStrIngredient() + ".png";
        Glide.with(holder.img.getContext())
                .load(imageUrl)
                .into(holder.img);  // Load image into the ImageView

        // Set the ingredient name in the TextView
        holder.txtView.setText(ingredients.getStrIngredient());

        // Set a click listener for the ingredient image
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Notify the listener when the ingredient is clicked
                if (listener != null) {
                    listener.onIngredientClick(ingredients);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        if (ingredientsList == null) {
            return 0;  // Return 0 if the list is null
        }
        else {
            return ingredientsList.size();  // Return the total number of categories
        }
    }

    // Method to update the category list
    public void setList(List<Ingredients> ingredients) {
        this.ingredientsList = ingredients;  // Update the category list
        notifyDataSetChanged();  // Notify the adapter to refresh the view
    }
}
