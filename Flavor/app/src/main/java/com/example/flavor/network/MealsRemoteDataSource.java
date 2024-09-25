package com.example.flavor.network;

import com.example.flavor.model.Category;
import com.example.flavor.model.Meal;

public interface MealsRemoteDataSource {
    public void makeNetworkCallRandomMeal(NetworkCallback<Meal> networkCallback);
    public void makeNetworkCallGetCategory(NetworkCallback<Category> networkCallback);
}
