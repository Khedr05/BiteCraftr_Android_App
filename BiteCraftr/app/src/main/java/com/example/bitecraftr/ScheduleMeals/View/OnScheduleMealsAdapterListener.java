package com.example.bitecraftr.ScheduleMeals.View;

import com.example.bitecraftr.Model.ScheduledMeal;

/**
 * OnScheduleMealsAdapterListener is an interface that defines a listener for handling user interactions
 * with scheduled meals in an adapter (usually a RecyclerView adapter).
 */
public interface OnScheduleMealsAdapterListener {
    /**
     * Callback method triggered when a user clicks to remove a scheduled meal.
     *
     * @param meal The ScheduledMeal object that the user wants to remove.
     */
    public void onRemoveMealClick(ScheduledMeal meal);
}
