package com.example.bitecraftr.FavouriteMeals.Presenter;

import androidx.lifecycle.LiveData;

import com.example.bitecraftr.FavouriteMeals.View.FavMealsView;
import com.example.bitecraftr.Model.AppRepository;
import com.example.bitecraftr.Model.Meal;

import java.util.List;

// Implementation of the FavMealsPresenter interface that handles favorite meal data
public class FavMealsPresnterImpl implements FavMealsPresenter {

    // Reference to the view interface (FavMealsView) to interact with the UI layer
    private FavMealsView _view;

    // Reference to the repository (AppRepository) that handles data operations
    private AppRepository _repo;

    // Constructor that initializes the presenter with the provided view and repository
    public FavMealsPresnterImpl(FavMealsView view, AppRepository repository) {
        this._view = view;
        this._repo = repository;
    }

    // Method to load the list of favorite meals from the repository
    // Returns LiveData<List<Meal>> to allow real-time UI updates
    @Override
    public LiveData<List<Meal>> loadFavMeals() {
        return _repo.fetchFavouriteMeals();
    }

    // Method to remove a meal from the favorites in the repository
    // This will delete the selected meal from the favorites list
    @Override
    public void removeFavMeal(Meal meal) {
        _repo.deleteFavouriteMeal(meal);
    }
}
