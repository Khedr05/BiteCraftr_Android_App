package com.example.bitecraftr.MealDetails.Presenter;

import com.example.bitecraftr.MealDetails.View.MealDetailsView;
import com.example.bitecraftr.Model.AppRepository;
import com.example.bitecraftr.Model.Ingredients;
import com.example.bitecraftr.Model.Meal;
import com.example.bitecraftr.Model.ScheduledMeal;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the MealDetailsPresenter interface.
 * This class handles the logic for meal details, including
 * loading meal information and ingredients, adding meals to
 * favorites, and scheduling meals.
 */
public class MealDetailsPresenterImpl implements MealDetailsPresenter {

    private final MealDetailsView _view;  // Reference to the view that displays meal details
    private final AppRepository _repo;     // Repository for data operations

    /**
     * Constructor for MealDetailsPresenterImpl.
     *
     * @param _view The view interface for displaying meal details.
     * @param _repo The repository for data access.
     */
    public MealDetailsPresenterImpl(MealDetailsView _view, AppRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void loadMealDetails(Meal meal) {
        // Display the meal details in the view
        _view.displayMealDetails(meal);
        // Load the ingredients for the meal
        loadIngredients(meal);
    }

    @Override
    public void loadIngredients(Meal meal) {
        List<Ingredients> ingredients = new ArrayList<>(); // List to hold the ingredients
        for (int i = 1; i <= 20; i++) { // Loop through potential ingredient slots
            try {
                // Dynamically invoke the getter methods for ingredient and measure
                String ingredient = (String) meal.getClass().getMethod("getStrIngredient" + i).invoke(meal);
                String measure = (String) meal.getClass().getMethod("getStrMeasure" + i).invoke(meal);

                // Check if both ingredient and measure are valid
                if (ingredient != null && !ingredient.isEmpty() && measure != null && !measure.isEmpty()) {
                    // Create the image URL for the ingredient
                    String imageUrl = "https://www.themealdb.com/images/ingredients/" + ingredient + ".png";
                    ingredients.add(new Ingredients(ingredient, measure, imageUrl,"","")); // Add ingredient to the list
                }
            } catch (Exception e) {
                // Show a toast message if there's an error fetching ingredients
                _view.showToast("Error fetching ingredients: " + e.getMessage());
            }
        }
        // Display the list of ingredients in the view
        _view.displayIngredients(ingredients);
    }

    @Override
    public void addToFavorite(Meal meal) {
        _repo.insertFavouriteMeal(meal); // Insert the meal into favorites in the repository
        _view.showToast("Added to favorites"); // Notify the user
    }

    @Override
    public void addToPlan(ScheduledMeal scheduledMeal) {
        _repo.insertScheduledMeal(scheduledMeal); // Insert the scheduled meal into the repository
        _view.showToast("Added to plan"); // Notify the user
    }
}
