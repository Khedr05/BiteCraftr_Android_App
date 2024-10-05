package com.example.bitecraftr.Search.Presenter;

import com.example.bitecraftr.Model.AppRepository;
import com.example.bitecraftr.Model.Meal;
import com.example.bitecraftr.Network.NetworkCallback;
import com.example.bitecraftr.Search.View.SearchView;

import java.util.List;

/**
 * This class is the implementation of the SearchPresenter interface.
 * It handles interactions between the view (SearchView) and the data repository (AppRepository).
 * It also handles the response from the network requests via the NetworkCallback interface.
 */
public class SearchPresenterImpl implements SearchPresenter, NetworkCallback<Meal> {

    // Reference to the view that will display the data
    private final SearchView _view;

    // Reference to the repository that fetches the data
    private final AppRepository _repo;

    /**
     * Constructor to initialize the SearchPresenterImpl.
     *
     * @param view The view that will display the data.
     * @param repo The repository responsible for fetching the data.
     */
    public SearchPresenterImpl(SearchView view, AppRepository repo) {
        _view = view;
        _repo = repo;
    }

    /**
     * This method is called when the network request is successful.
     * It sends the list of meals retrieved to the view for display.
     *
     * @param resultList List of meals retrieved from the repository.
     */
    @Override
    public void onSuccess(List<Meal> resultList) {
        _view.showData(resultList); // Pass the result list to the view for display
    }

    /**
     * This method is called when the network request fails.
     * It sends the error message to the view to display an error.
     *
     * @param errorMessage The error message explaining the failure.
     */
    @Override
    public void onFailure(String errorMessage) {
        _view.showErrorMsg(errorMessage); // Pass the error message to the view
    }

    /**
     * This method triggers fetching a meal by its ID.
     * It calls the repository to fetch the meal and provides a callback to handle the result.
     *
     * @param mealId The ID of the meal to fetch.
     */
    @Override
    public void getMealById(String mealId) {
        _repo.fetchMealById(mealId, this); // Fetch meal by ID from the repository
    }

    /**
     * This method triggers fetching a meal by its name.
     * It calls the repository to fetch the meal and provides a callback to handle the result.
     *
     * @param mealName The name of the meal to fetch.
     */
    @Override
    public void getMealByName(String mealName) {
        _repo.fetchMealByName(mealName, this); // Fetch meal by name from the repository
    }

    /**
     * This method triggers fetching meals by a category.
     * It calls the repository to fetch the meals by category and provides a callback to handle the result.
     *
     * @param categoryName The category of meals to fetch.
     */
    @Override
    public void getMealsByCategory(String categoryName) {
        _repo.fetchMealByCategory(categoryName, this); // Fetch meals by category from the repository
    }

    /**
     * This method triggers fetching meals by a country.
     * It calls the repository to fetch the meals by country and provides a callback to handle the result.
     *
     * @param countryName The country name used to fetch meals.
     */
    @Override
    public void getMealsByCountry(String countryName) {
        _repo.fetchMealByCountry(countryName, this); // Fetch meals by country from the repository
    }

    /**
     * This method triggers fetching meals by an ingredient.
     * It calls the repository to fetch the meals by ingredient and provides a callback to handle the result.
     *
     * @param ingredientName The ingredient name used to fetch meals.
     */
    @Override
    public void getMealsByIngredients(String ingredientName) {
        _repo.fetchMealByIngredient(ingredientName, this); // Fetch meals by ingredient from the repository
    }
}
