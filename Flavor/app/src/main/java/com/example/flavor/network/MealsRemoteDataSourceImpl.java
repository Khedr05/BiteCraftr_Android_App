package com.example.flavor.network;

import androidx.annotation.NonNull;

import com.example.flavor.model.Category;
import com.example.flavor.model.Meal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteDataSourceImpl implements MealsRemoteDataSource{

    public static final String TAG = "MealsActivity";
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private MealService mealService;
    private static MealsRemoteDataSourceImpl client = null;

    private MealsRemoteDataSourceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mealService = retrofit.create(MealService.class);
    }

    public static MealsRemoteDataSourceImpl getInstance() {
        if (client == null) {
            client = new MealsRemoteDataSourceImpl();
        }
        return client;
    }


    @Override
    public void makeNetworkCallRandomMeal(NetworkCallback<Meal> networkCallback) {
        mealService.getRandomMeal().enqueue(new Callback<AllResponse<Meal>>() {

            @Override
            public void onResponse(@NonNull Call<AllResponse<Meal>> call, @NonNull retrofit2.Response<AllResponse<Meal>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallback.onSuccessResult(response.body().meals);
                } else {
                    networkCallback.onFailureResult("Failed to fetch random meal");
                }
            }

            @Override
            public void onFailure(Call<AllResponse<Meal>> call, Throwable throwable) {
                networkCallback.onFailureResult(throwable.getMessage());
            }
        });
    }

    @Override
    public void makeNetworkCallGetCategory(NetworkCallback<Category> networkCallback) {
        mealService.getMealsCategories().enqueue(new Callback<AllResponse<Category>>() {

            @Override
            public void onResponse(@NonNull Call<AllResponse<Category>> call, @NonNull retrofit2.Response<AllResponse<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallback.onSuccessResult(response.body().categories);
                } else {
                    networkCallback.onFailureResult("Failed to fetch category");
                }
            }

            @Override
            public void onFailure(Call<AllResponse<Category>> call, Throwable throwable) {
                networkCallback.onFailureResult(throwable.getMessage());
            }
        });
    }
}

