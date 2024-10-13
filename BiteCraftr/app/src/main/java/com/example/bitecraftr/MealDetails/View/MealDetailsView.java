package com.example.bitecraftr.MealDetails.View;

import com.example.bitecraftr.Model.Ingredients;
import com.example.bitecraftr.Model.Meal;

import java.util.List;

// Interface defining the contract for MealDetails view
public interface MealDetailsView {
    // Method to display the details of a meal
    void displayMealDetails(Meal meal);

    // Method to display a list of ingredients associated with a meal
    void displayIngredients(List<Ingredients> ingredients);

    // Method to show a toast message to the user
    void showSnakebar(String message);
}
