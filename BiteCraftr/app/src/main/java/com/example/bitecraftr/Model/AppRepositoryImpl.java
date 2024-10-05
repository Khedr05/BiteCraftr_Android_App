package com.example.bitecraftr.Model;

import androidx.lifecycle.LiveData;

import com.example.bitecraftr.Database.LocalDataSource;
import com.example.bitecraftr.Network.NetworkCallback;
import com.example.bitecraftr.Network.RemoteDataSource;

import java.util.List;

/**
 * Implementation of the AppRepository interface, handling data operations from both
 * network and local sources (e.g., fetching meals, storing favorites).
 */
public class AppRepositoryImpl implements AppRepository {

    // Remote data source for network operations
    private RemoteDataSource remoteDataSource;

    // Local data source for database operations
    private LocalDataSource localDataSource;

    // Singleton instance of the repository
    private static AppRepositoryImpl instance = null;

    /**
     * Private constructor to initialize the repository with remote and local data sources.
     *
     * @param remoteDataSource Source for network data
     * @param localDataSource Source for local database operations
     */
    private AppRepositoryImpl(RemoteDataSource remoteDataSource, LocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    /**
     * Returns the singleton instance of the repository.
     *
     * @param remoteDataSource Source for network data
     * @param localDataSource Source for local database operations
     * @return Singleton instance of the repository
     */
    public static AppRepositoryImpl getInstance(RemoteDataSource remoteDataSource, LocalDataSource localDataSource) {
        if (instance == null) {
            instance = new AppRepositoryImpl(remoteDataSource, localDataSource);
        }
        return instance;
    }

    /**
     * Fetches a random meal from the network using the remote data source.
     *
     * @param networkCallback Callback to handle the random meal response
     */
    @Override
    public void fetchRandomMeal(NetworkCallback<Meal> networkCallback) {
        remoteDataSource.fetchRandomMeal(networkCallback);
    }

    /**
     * Fetches a meal by its name from the network using the remote data source.
     *
     * @param mealName Name of the meal to fetch
     * @param networkCallback Callback to handle the meal response
     */
    @Override
    public void fetchMealByName(String mealName, NetworkCallback<Meal> networkCallback) {
        remoteDataSource.fetchMealByName(mealName, networkCallback);
    }

    /**
     * Fetches a meal by its unique ID from the network using the remote data source.
     *
     * @param mealId ID of the meal to fetch
     * @param networkCallback Callback to handle the meal response
     */
    @Override
    public void fetchMealById(String mealId, NetworkCallback<Meal> networkCallback) {
        remoteDataSource.fetchMealById(mealId, networkCallback);
    }

    /**
     * Fetches meals by the country of origin from the network.
     *
     * @param country Name of the country
     * @param networkCallback Callback to handle the meal response
     */
    @Override
    public void fetchMealByCountry(String country, NetworkCallback<Meal> networkCallback) {
        remoteDataSource.fetchMealsByCountry(country, networkCallback);
    }

    /**
     * Fetches meals by a specific ingredient from the network.
     *
     * @param ingredient Name of the ingredient
     * @param networkCallback Callback to handle the meal response
     */
    @Override
    public void fetchMealByIngredient(String ingredient, NetworkCallback<Meal> networkCallback) {
        remoteDataSource.fetchMealsByIngredient(ingredient, networkCallback);
    }

    /**
     * Fetches the list of meal categories from the network.
     *
     * @param networkCallback Callback to handle the category response
     */
    @Override
    public void fetchMealCategories(NetworkCallback<Category> networkCallback) {
        remoteDataSource.fetchMealCategories(networkCallback);
    }

    /**
     * Fetches meals by their category ID from the network.
     *
     * @param categoryId ID of the category
     * @param networkCallback Callback to handle the meal response
     */
    @Override
    public void fetchMealByCategory(String categoryId, NetworkCallback<Meal> networkCallback) {
        remoteDataSource.fetchMealsByCategoryId(categoryId, networkCallback);
    }

    /**
     * Retrieves a list of favorite meals stored in the local database.
     *
     * @return LiveData containing a list of favorite meals
     */
    @Override
    public LiveData<List<Meal>> fetchFavouriteMeals() {
        return localDataSource.loadFavouriteMeals();
    }

    /**
     * Inserts a meal into the local database as a favorite meal.
     *
     * @param meal Meal to be inserted
     */
    @Override
    public void insertFavouriteMeal(Meal meal) {
        localDataSource.insertFavouriteMeal(meal);
    }

    /**
     * Deletes a meal from the local database.
     *
     * @param meal Meal to be deleted
     */
    @Override
    public void deleteFavouriteMeal(Meal meal) {
        localDataSource.deleteFavouriteMeal(meal);
    }

    /**
     * Retrieves a list of scheduled meals for a specific date from the local database.
     *
     * @param date Date for which scheduled meals are to be retrieved
     * @return LiveData containing a list of scheduled meals
     */
    @Override
    public LiveData<List<ScheduledMeal>> fetchScheduledMeals(String date) {
        return localDataSource.loadScheduledMeals(date);
    }

    /**
     * Inserts a scheduled meal into the local database.
     *
     * @param meal Scheduled meal to be inserted
     */
    @Override
    public void insertScheduledMeal(ScheduledMeal meal) {
        localDataSource.insertScheduledMeal(meal);
    }

    /**
     * Deletes a meal from the local database.
     *
     * @param meal Meal to be deleted
     */
    @Override
    public void deleteScheduledMeal(ScheduledMeal meal) {
        localDataSource.deleteScheduledMeal(meal);
    }

    @Override
    public void fetchCountries(NetworkCallback<Country> networkCallback) {
        remoteDataSource.fetchCountries(networkCallback);
    }

    @Override
    public void fetchIngredients(NetworkCallback<Ingredients> networkCallback) {
        remoteDataSource.fetchIngredients(networkCallback);
    }
}
