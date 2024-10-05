package com.example.bitecraftr.Explore.Presenter;

// Define an interface for the Explore feature's presenter layer
public interface ExplorePresenter {

    // Method to fetch a random meal. The implementation will handle the logic to retrieve this data from a source.
    void exploreGetRandomMeal();

    // Method to fetch all meal categories. The implementation will handle the retrieval of meal categories.
    void exploreGetCategories();

    // Method to fetch meals by a specific category. The 'mealId' parameter identifies the category to filter meals.
    void exploreGetMealsByCategory(String mealId);

    // Method to fetch meals by a specific ingredient. The 'mealId' parameter identifies the ingredient to filter meals.
    void exploreGetMealById(String mealId);

    // Method to fetch countries. The implementation will handle the retrieval of countries.
    void exploreGetCountries();

    // Method to fetch meals by a specific country. The 'countryName' parameter identifies the country to filter meals.
    void exploreGetMealsByCountry(String countryName);

    // Method to fetch ingredients. The implementation will handle the retrieval of ingredients.
    void exploreGetIngredients();

    // Method to fetch meals by a specific ingredient. The 'ingredientStr' parameter identifies the ingredient to filter meals.
    void exploreGetMealsByIngredients(String ingredientName);
}
