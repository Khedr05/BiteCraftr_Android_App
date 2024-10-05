package com.example.bitecraftr.Model;

import androidx.lifecycle.LiveData;

import com.example.bitecraftr.Network.NetworkCallback;

import java.util.List;

/**
 * Interface for the App Repository, responsible for managing meal data operations,
 * both from network sources and local storage.
 */
public interface AppRepository {

    /**
     * Fetches a random meal from the network.
     *
     * @param networkCallback Callback to handle the random meal response.
     */
    void fetchRandomMeal(NetworkCallback<Meal> networkCallback);

    /**
     * Retrieves a meal by its name from the network.
     *
     * @param mealName The name of the meal.
     * @param networkCallback Callback to handle the meal response.
     */
    void fetchMealByName(String mealName, NetworkCallback<Meal> networkCallback);

    /**
     * Retrieves a meal by its unique ID from the network.
     *
     * @param mealId The unique ID of the meal.
     * @param networkCallback Callback to handle the meal response.
     */
    void fetchMealById(String mealId, NetworkCallback<Meal> networkCallback);


    /**
     * Retrieves a meal by the specified country from the network.
     *
     * @param country The name of the country.
     * @param networkCallback Callback to handle the meal response.
     */
    void fetchMealByCountry(String country, NetworkCallback<Meal> networkCallback);

    /**
     * Retrieves a meal by its main ingredient from the network.
     *
     * @param ingredient The name of the ingredient.
     * @param networkCallback Callback to handle the meal response.
     */
    void fetchMealByIngredient(String ingredient, NetworkCallback<Meal> networkCallback);

    /**
     * Retrieves the list of meal categories from the network.
     *
     * @param networkCallback Callback to handle the meal categories response.
     */
    void fetchMealCategories(NetworkCallback<Category> networkCallback);

    /**
     * Retrieves a meal by its category from the network.
     *
     * @param categoryId The ID of the category.
     * @param networkCallback Callback to handle the meal response.
     */
    void fetchMealByCategory(String categoryId, NetworkCallback<Meal> networkCallback);

    /**
     * Retrieves favorite meals stored in local storage as LiveData.
     *
     * @return LiveData containing a list of favorite meals.
     */
    LiveData<List<Meal>> fetchFavouriteMeals();

    /**
     * Inserts a new meal into local storage.
     *
     * @param meal The meal to be inserted.
     */
    void insertFavouriteMeal(Meal meal);

    /**
     * Deletes a specified meal from local storage.
     *
     * @param meal The meal to be deleted.
     */
    void deleteFavouriteMeal(Meal meal);

    /**
     * Retrieves planned meals for a specific date from local storage.
     *
     * @param date The date for which planned meals are requested.
     * @return LiveData containing a list of scheduled meals.
     */
    LiveData<List<ScheduledMeal>> fetchScheduledMeals(String date);

    /**
     * Inserts a new scheduled meal into local storage.
     *
     * @param meal The scheduled meal to be inserted.
     */
    void insertScheduledMeal(ScheduledMeal meal);

    /**
     * Deletes a specified meal from local storage.
     *
     * @param meal The meal to be deleted.
     */
    void deleteScheduledMeal(ScheduledMeal meal);


    void fetchCountries(NetworkCallback<Country> networkCallback);
    void fetchIngredients(NetworkCallback<Ingredients> networkCallback);
}
