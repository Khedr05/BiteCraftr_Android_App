package com.example.bitecraftr.Network;


import com.example.bitecraftr.Model.Category;
import com.example.bitecraftr.Model.Country;
import com.example.bitecraftr.Model.Ingredients;
import com.example.bitecraftr.Model.Meal;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.Call;


public interface ApiServices {

    // Fetch a random meal from the API
    @GET("random.php")
    Call<ApiResponse<Meal>> fetchRandomMeal();

    // Fetch meals by name search from the API
    @GET("search.php")
    Call<ApiResponse<Meal>> searchMealsByName(@Query("s") String mealName);

    // Fetch a meal by its unique ID from the API
    @GET("lookup.php")
    Call<ApiResponse<Meal>> fetchMealById(@Query("i") String id);

    // Fetch meals by ingredient from the API
    @GET("filter.php")
    Call<ApiResponse<Meal>> fetchMealsByIngredient(@Query("i") String ingredient);

    // Fetch meals by country or area from the API
    @GET("filter.php")
    Call<ApiResponse<Meal>> fetchMealsByCountry(@Query("a") String country);

    // Fetch all meal categories from the API
    @GET("categories.php")
    Call<ApiResponse<Category>> fetchMealCategories();

    // Fetch meals by category ID from the API
    @GET("filter.php")
    Call<ApiResponse<Meal>> fetchMealsByCategory(@Query("c") String category);

    @GET("list.php?a=list")
    Call<ApiResponse<Country>> fetchCountries();

    @GET("list.php?i=list")
    Call<ApiResponse<Ingredients>> fetchIngredients();

}
