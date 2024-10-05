package com.example.bitecraftr.Explore.View;

import com.example.bitecraftr.Model.Category;

// Interface for handling category click events in the CategoriesAdapter
public interface CategoriesAdapterListener {
    // Method to be called when a category is clicked
    void onCategoryClick(Category category);
}
