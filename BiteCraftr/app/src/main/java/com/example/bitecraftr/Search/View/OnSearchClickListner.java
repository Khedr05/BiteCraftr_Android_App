package com.example.bitecraftr.Search.View;

/**
 * Interface for handling clicks on search results.
 * Implementations of this interface will define what happens when a meal is clicked.
 */
public interface OnSearchClickListner {

    /**
     * This method will be triggered when a meal is clicked.
     *
     * @param mealId The ID of the meal that was clicked.
     */
    public void onMealClickListner(String mealId);
}
