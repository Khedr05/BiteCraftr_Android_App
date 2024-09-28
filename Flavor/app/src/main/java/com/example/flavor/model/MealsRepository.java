package com.example.flavor.model;

import androidx.lifecycle.LiveData;

import com.example.flavor.network.NetworkCallback;

import java.util.List;

public interface MealsRepository{
    public void getRandomMeal(NetworkCallback<Meal> networkCallback);
    public void getCategories(NetworkCallback<Category> networkCallback);
    public LiveData<List<Meal>> getStoredMeals();
    public void insertMeal(Meal meal);
    public void deleteMeal(Meal meal);
}
