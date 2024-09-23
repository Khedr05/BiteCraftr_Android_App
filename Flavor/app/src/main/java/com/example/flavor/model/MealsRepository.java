package com.example.flavor.model;

import com.example.flavor.network.NetworkCallback;

public interface MealsRepository {
    public void getRandomMeal(NetworkCallback networkCallback);
}
