package com.example.bitecraftr.FavouriteMeals.View;

import com.example.bitecraftr.Model.Meal;

// Listener interface for handling adapter events
public interface OnFavMealsAdapterListener {
    // Called when the user clicks to remove a favorite meal
    void onFavRemoveMealClick(Meal meal);
}
