package com.example.flavor.favMeals.view;

import com.example.flavor.model.Meal;

import java.util.List;

public interface FavMealsInterface {
    public void showMeal(List<Meal> meal);
    public void showErrMsg(String errorMsg);
}
