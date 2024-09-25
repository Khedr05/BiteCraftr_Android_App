package com.example.flavor.network;

import com.example.flavor.model.Category;
import com.example.flavor.model.Meal;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealService {

   @GET("random.php")
   Call<AllResponse<Meal>> getRandomMeal();

   @GET("categories.php")
   Call <AllResponse<Category>> getMealsCategories();





}
