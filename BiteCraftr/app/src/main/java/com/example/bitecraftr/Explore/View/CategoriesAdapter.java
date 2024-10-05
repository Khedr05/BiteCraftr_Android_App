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
import com.bumptech.glide.request.RequestOptions;
import com.example.bitecraftr.Model.Category;
import com.example.bitecraftr.R;

import java.util.List;

// Adapter class for displaying categories in a RecyclerView
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private final Context context;  // Context for inflating views
    private List<Category> categoryList;  // List of categories to display
    private CategoriesAdapterListener listener;  // Listener for category clicks

    // Constructor to initialize context, category list, and listener
    public CategoriesAdapter(Context context, List<Category> categories, CategoriesAdapterListener listener) {
        this.context = context;
        this.categoryList = categories;
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
            img = v.findViewById(R.id.catImage);  // Initialize the ImageView
            txtView = v.findViewById(R.id.catTitle);  // Initialize the TextView
            constraintLayout = v.findViewById(R.id.main);  // Initialize the ConstraintLayout
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        // Inflate the layout for each category item
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.explore_categories_row, recyclerView, false);
        return new ViewHolder(v);  // Return a new ViewHolder instance
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the current category based on its position
        Category category = categoryList.get(position);

        // Load the category image using Glide
        Glide.with(context).load(category.getStrCategoryThumb())
                .apply(new RequestOptions().override(200, 200)  // Set image size
                        .placeholder(R.drawable.ic_launcher_background)  // Placeholder image
                        .error(R.drawable.ic_launcher_foreground))  // Error image
                .into(holder.img);  // Set the image in the ImageView

        holder.txtView.setText(category.getStrCategory());  // Set the category title

        // Set a click listener for the category image
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Notify the listener when the category is clicked
                if (listener != null) {
                    listener.onCategoryClick(category);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (categoryList == null) {
            return 0;  // Return 0 if the list is null
        }
        else{
            return categoryList.size();  // Return the total number of categories
        }
    }

    // Method to update the category list
    public void setList(List<Category> categories) {
        this.categoryList = categories;  // Update the category list
    }
}
