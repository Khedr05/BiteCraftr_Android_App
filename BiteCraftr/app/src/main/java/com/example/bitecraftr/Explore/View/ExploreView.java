package com.example.bitecraftr.Explore.View;

import com.example.bitecraftr.Model.Category;
import com.example.bitecraftr.Model.Country;
import com.example.bitecraftr.Model.Ingredients;
import com.example.bitecraftr.Model.Meal;

import java.util.List;

// Interface to define methods for the Explore view
public interface ExploreView {

    // Method to display a list of random meals
    public void exploreShowRandomMeals(List<Meal> meals);

    // Method to display a list of meal categories
    public void exploreShowCategories(List<Category> categories);

    // Method to display a list of meals filtered by category
    public void exploreShowMealsByCategory(List<Meal> meals);

    // Method to show an error message
    public void exploreShowErrorMsg(String error);

    // Method to show a list of meals filtered by country
    public void exploreShowMealDetails(List<Meal> meal);

    // Method to show a list of countries
    public void exploreShowCountries(List<Country> countries);

    // Method to show a list of meals filtered by country
    public void exploreShowMealsByCountry(List<Meal> meals);

    // Method to show a list of ingredients
    public void exploreShowIngredients(List<Ingredients> ingredients);

    // Method to show a list of meals filtered by ingredients
    public void exploreShowMealsByIngredients(List<Meal> meals);

}
