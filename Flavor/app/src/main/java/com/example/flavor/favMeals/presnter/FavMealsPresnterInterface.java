package com.example.flavor.favMeals.presnter;

import androidx.lifecycle.LiveData;

import com.example.flavor.model.Meal;

import java.util.List;

public interface FavMealsPresnterInterface {
    LiveData<List<Meal>> loadFavMeals();
    void removeMeal(Meal meal);
}
