package com.example.bitecraftr.Network;

import com.example.bitecraftr.Model.Category;
import com.example.bitecraftr.Model.Country;
import com.example.bitecraftr.Model.Ingredients;
import com.example.bitecraftr.Model.Meal;

public interface RemoteDataSource {

    // Initiates a network call to retrieve a random meal.
    public void fetchRandomMeal(NetworkCallback<Meal> mealCallback);

    // Initiates a network call to retrieve meals by name.
    public void fetchMealByName(String mealName, NetworkCallback<Meal> mealCallback);

    // Initiates a network call to retrieve a meal by its unique ID.
    public void fetchMealById(String mealId, NetworkCallback<Meal> mealCallback);

    // Initiates a network call to retrieve meals by ingredient.
    public void fetchMealsByIngredient(String ingredientName, NetworkCallback<Meal> mealCallback);

    // Initiates a network call to retrieve meals by country.
    public void fetchMealsByCountry(String countryName, NetworkCallback<Meal> mealCallback);

    // Initiates a network call to retrieve all meal categories.
    public void fetchMealCategories(NetworkCallback<Category> categoryCallback);

    // Initiates a network call to retrieve meals by category ID.
    public void fetchMealsByCategoryId(String categoryId, NetworkCallback<Meal> mealCallback);

    public void fetchCountries(NetworkCallback<Country> countryCallback);
    public void fetchIngredients(NetworkCallback<Ingredients> ingredientCallback);
}
