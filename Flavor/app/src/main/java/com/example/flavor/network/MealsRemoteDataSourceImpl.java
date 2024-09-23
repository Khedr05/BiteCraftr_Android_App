package com.example.flavor.network;

import com.example.flavor.network.responses.RandomMealResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
    public void makeNetworkCallRandomMeal(NetworkCallback networkCallback) {
        mealService.getRandomMeal().enqueue(new Callback<RandomMealResponse>() {
            @Override
            public void onResponse(Call<RandomMealResponse> call, Response<RandomMealResponse> response) {
                if(response.isSuccessful()){
                    networkCallback.onSuccessResult(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<RandomMealResponse> call, Throwable throwable) {
                networkCallback.onFailureResult(throwable.getMessage());
            }
        });
    }
}

