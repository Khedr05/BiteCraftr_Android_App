package com.example.bitecraftr.Explore.Presenter;

import com.example.bitecraftr.Explore.View.ExploreView;
import com.example.bitecraftr.Model.AppRepository;
import com.example.bitecraftr.Network.NetworkCallback;

import java.util.List;

// Implementation of the ExplorePresenter interface
public class ExplorePresenterImpl implements ExplorePresenter {
    private final ExploreView _view;  // Reference to the view interface for updating the UI
    private final AppRepository _repo; // Reference to the repository for data operations

    // Constructor to initialize the presenter with a view and a repository
    public ExplorePresenterImpl(ExploreView view, AppRepository repo) {
        _view = view;   // Set the view
        _repo = repo;   // Set the repository
    }

    // Fetches a random meal and updates the view with the result
    @Override
    public void exploreGetRandomMeal() {
        // Call repository to fetch a random meal and handle the response using the GenericCallback
        _repo.fetchRandomMeal(new GenericCallback<>(_view::exploreShowRandomMeals, _view::exploreShowErrorMsg));
    }

    // Fetches meal categories and updates the view with the result
    @Override
    public void exploreGetCategories() {
        // Call repository to fetch meal categories and handle the response using the GenericCallback
        _repo.fetchMealCategories(new GenericCallback<>(_view::exploreShowCategories, _view::exploreShowErrorMsg));
    }

    // Fetches meals by category and updates the view with the result
    @Override
    public void exploreGetMealsByCategory(String mealId) {
        // Call repository to fetch meals by category and handle the response using the GenericCallback
        _repo.fetchMealByCategory(mealId, new GenericCallback<>(_view::exploreShowMealsByCategory, _view::exploreShowErrorMsg));
    }

    @Override
    public void exploreGetMealById(String mealId) {
        _repo.fetchMealById(mealId,new GenericCallback<>(_view::exploreShowMealDetails, _view::exploreShowErrorMsg));
    }

    @Override
    public void exploreGetCountries() {
        _repo.fetchCountries(new GenericCallback<>(_view::exploreShowCountries, _view::exploreShowErrorMsg));
    }

    @Override
    public void exploreGetMealsByCountry(String countryName) {
        _repo.fetchMealByCountry(countryName, new GenericCallback<>(_view::exploreShowMealsByCountry , _view::exploreShowErrorMsg));
    }

    @Override
    public void exploreGetIngredients() {
        _repo.fetchIngredients(new GenericCallback<>(_view::exploreShowIngredients, _view::exploreShowErrorMsg));
    }

    @Override
    public void exploreGetMealsByIngredients(String ingredientName) {
        _repo.fetchMealByIngredient(ingredientName,new GenericCallback<>(_view::exploreShowMealsByIngredients, _view::exploreShowErrorMsg));
    }

    // Generic callback class to avoid repeating similar code for network callbacks
    private static class GenericCallback<T> implements NetworkCallback<T> {
        private final OnSuccess<T> onSuccess; // Success callback
        private final OnError onError;         // Error callback

        // Constructor to initialize success and error callbacks
        GenericCallback(OnSuccess<T> onSuccess, OnError onError) {
            this.onSuccess = onSuccess;
            this.onError = onError;
        }

        // Invoked on successful network response
        @Override
        public void onSuccess(List<T> resultList) {
            onSuccess.invoke(resultList); // Pass the result to the success handler
        }

        // Invoked on network failure
        @Override
        public void onFailure(String errorMessage) {
            onError.invoke(errorMessage); // Pass the error message to the error handler
        }
    }

    // Functional interface for success callback
    @FunctionalInterface
    private interface OnSuccess<T> {
        void invoke(List<T> data); // Method to handle success response
    }

    // Functional interface for error callback
    @FunctionalInterface
    private interface OnError {
        void invoke(String errorMsg); // Method to handle error response
    }
}
