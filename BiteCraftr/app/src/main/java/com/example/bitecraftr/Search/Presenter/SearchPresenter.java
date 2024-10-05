package com.example.bitecraftr.Search.Presenter;

/**
 * Interface for handling search operations related to meals.
 */
public interface SearchPresenter {

    /**
     * Fetches a meal by its unique identifier.
     * @param mealId The ID of the meal.
     */
    void getMealById(String mealId);

    /**
     * Fetches meals based on the provided meal name.
     * @param mealName The name of the meal to search for.
     */
    void getMealByName(String mealName);

    /**
     * Fetches meals that belong to the specified category.
     * @param categoryName The category name of the meals.
     */
    void getMealsByCategory(String categoryName);

    /**
     * Fetches meals that originate from the specified country.
     * @param countryName The country name of the meals.
     */
    void getMealsByCountry(String countryName);

    /**
     * Fetches meals that contain the specified ingredient.
     * @param ingredientName The name of the ingredient to search for.
     */
    void getMealsByIngredients(String ingredientName);
}
