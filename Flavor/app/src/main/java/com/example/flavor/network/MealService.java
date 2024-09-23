package com.example.flavor.network;

import com.example.flavor.network.responses.CategoryResponse;
import com.example.flavor.network.responses.RandomMealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {
   // @GET("search.php")
   // Call<MealResponse> searchMeals(@Query("s") String searchQuery);


   //get all categories
   @GET("categories.php")
   Call<CategoryResponse> getAllCategories();

   //get a random meal
   @GET("random.php")
   Call<RandomMealResponse> getRandomMeal();

}
