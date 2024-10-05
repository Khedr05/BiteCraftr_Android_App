package com.example.bitecraftr.FavouriteMeals.Presenter;

import androidx.lifecycle.LiveData;

import com.example.bitecraftr.Model.Meal;

import java.util.List;

// Interface to define the contract for handling favorite meals functionality in the presenter
public interface FavMealsPresenter {

    // Method to load all the favorite meals from the data source and return them as LiveData
    // LiveData is used here to observe the data changes (useful for UI updates in real-time)
    public LiveData<List<Meal>> loadFavMeals();

    // Method to remove a specific meal from the list of favorite meals
    // This will be called when the user wants to unfavorite a meal
    public void removeFavMeal(Meal meal);
}
