package com.example.bitecraftr.MealDetails.Presenter;

import com.example.bitecraftr.Model.Meal;
import com.example.bitecraftr.Model.ScheduledMeal;

/**
 * Interface for the MealDetailsPresenter in the MealDetails feature.
 * This interface defines the methods that the presenter must implement
 * to handle the meal details logic.
 */
public interface MealDetailsPresenter {

    /**
     * Loads the details of the specified meal.
     *
     * @param meal The Meal object containing the details to be loaded.
     */
    void loadMealDetails(Meal meal);

    /**
     * Loads the ingredients for the specified meal.
     *
     * @param meal The Meal object for which the ingredients are to be loaded.
     */
    void loadIngredients(Meal meal);

    /**
     * Adds the specified meal to the user's favorites.
     *
     * @param meal The Meal object to be added to favorites.
     */
    void addToFavorite(Meal meal);

    /**
     * Adds a scheduled meal to the user's meal plan.
     *
     * @param scheduledMeal The ScheduledMeal object containing the meal and its planned date.
     */
    void addToPlan(ScheduledMeal scheduledMeal);
}
