package com.example.flavor.model;

import com.example.flavor.network.NetworkCallback;

public interface MealsRepository{
    public void getRandomMeal(NetworkCallback<Meal> networkCallback);
    public void getCategories(NetworkCallback<Category> networkCallback);
}
