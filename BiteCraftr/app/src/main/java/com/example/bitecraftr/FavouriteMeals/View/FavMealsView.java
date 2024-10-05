package com.example.bitecraftr.FavouriteMeals.View;

import com.example.bitecraftr.Model.Meal;

import java.util.List;

// Interface for the Favorite Meals View, defining the methods the view will implement
public interface FavMealsView {

    // Method to display a list of favorite meals in the view
    public void favShowMeals(List<Meal> meal);

    // Method to show an error message when something goes wrong
    public void favShowErrorMsg(String errorMsg);
}
