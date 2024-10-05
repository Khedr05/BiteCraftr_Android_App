package com.example.bitecraftr.Network;

import androidx.annotation.NonNull;

import com.example.bitecraftr.Model.Category;
import com.example.bitecraftr.Model.Country;
import com.example.bitecraftr.Model.Ingredients;
import com.example.bitecraftr.Model.Meal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSourceImpl implements RemoteDataSource {

    // Logging tag for this class
    public static final String LOG_TAG = "RemoteDataSourceImpl";

    // Base URL for the meal database API
    private static final String API_BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    // Instance of the API services
    private ApiServices apiServices;

    // Singleton instance of RemoteDataSourceImpl
    private static RemoteDataSourceImpl instance = null;

    // Private constructor for singleton implementation
    private RemoteDataSourceImpl() {
        // Create a Retrofit instance with the specified base URL and Gson converter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // Create an instance of the API services interface
        apiServices = retrofit.create(ApiServices.class);
    }

    // Method to get the singleton instance of RemoteDataSourceImpl
    public static RemoteDataSourceImpl getInstance() {
        // Create a new instance if it doesn't exist
        if (instance == null) {
            instance = new RemoteDataSourceImpl();
        }
        return instance; // Return the singleton instance
    }

    @Override
    public void fetchRandomMeal(NetworkCallback<Meal> mealCallback) {
        // Make an asynchronous network call to fetch a random meal
        apiServices.fetchRandomMeal().enqueue(new Callback<ApiResponse<Meal>>() {
            @Override
            public void onResponse(Call<ApiResponse<Meal>> call, Response<ApiResponse<Meal>> response) {
                // Check if the response is successful and contains a valid body
                if (response.isSuccessful() && response.body() != null) {
                    mealCallback.onSuccess(response.body().meals); // Pass the meal list to the callback on success
                } else {
                    mealCallback.onFailure("Failed to fetch random meal: Response was unsuccessful or empty."); // Handle failure case
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Meal>> call, Throwable throwable) {
                mealCallback.onFailure("Failed to fetch random meal: " + throwable.getMessage()); // Handle failure and pass the error message
            }
        });
    }

    @Override
    public void fetchMealByName(String mealName, NetworkCallback<Meal> mealCallback) {
        // Make an asynchronous network call to fetch a meal by name
        apiServices.searchMealsByName(mealName).enqueue(new Callback<ApiResponse<Meal>>() {
            @Override
            public void onResponse(Call<ApiResponse<Meal>> call, Response<ApiResponse<Meal>> response) {
                // Check if the response is successful and contains a valid body
                if (response.isSuccessful() && response.body() != null) {
                    mealCallback.onSuccess(response.body().meals); // Pass the meal list to the callback on success
                } else {
                    mealCallback.onFailure("Failed to fetch meal by name: Response was unsuccessful or empty."); // Handle failure case
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Meal>> call, Throwable throwable) {
                mealCallback.onFailure("Failed to fetch meal by name: " + throwable.getMessage()); // Handle failure and pass the error message
            }
        });
    }

    @Override
    public void fetchMealById(String mealId, NetworkCallback<Meal> mealCallback) {
        // Make an asynchronous network call to fetch a meal by its ID
        apiServices.fetchMealById(mealId).enqueue(new Callback<ApiResponse<Meal>>() {
            @Override
            public void onResponse(Call<ApiResponse<Meal>> call, Response<ApiResponse<Meal>> response) {
                // Check if the response is successful and contains a valid body
                if (response.isSuccessful() && response.body() != null) {
                    mealCallback.onSuccess(response.body().meals); // Pass the meal list to the callback on success
                } else {
                    mealCallback.onFailure("Failed to fetch meal by id: Response was unsuccessful or empty."); // Handle failure case
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Meal>> call, Throwable throwable) {
                mealCallback.onFailure("Failed to fetch meal by id: " + throwable.getMessage()); // Handle failure and pass the error message
            }
        });
    }

    @Override
    public void fetchMealsByIngredient(String ingredientName, NetworkCallback<Meal> mealCallback) {
        // Make an asynchronous network call to fetch meals by ingredient
        apiServices.fetchMealsByIngredient(ingredientName).enqueue(new Callback<ApiResponse<Meal>>() {
            @Override
            public void onResponse(Call<ApiResponse<Meal>> call, Response<ApiResponse<Meal>> response) {
                // Check if the response is successful and contains a valid body
                if (response.isSuccessful() && response.body() != null) {
                    mealCallback.onSuccess(response.body().meals); // Pass the meal list to the callback on success
                } else {
                    mealCallback.onFailure("Failed to fetch meals by ingredient: Response was unsuccessful or empty."); // Handle failure case
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Meal>> call, Throwable throwable) {
                mealCallback.onFailure("Failed to fetch meals by ingredient: " + throwable.getMessage()); // Handle failure and pass the error message
            }
        });
    }

    @Override
    public void fetchMealsByCountry(String countryName, NetworkCallback<Meal> mealCallback) {
        // Make an asynchronous network call to fetch meals by country
        apiServices.fetchMealsByCountry(countryName).enqueue(new Callback<ApiResponse<Meal>>() {
            @Override
            public void onResponse(Call<ApiResponse<Meal>> call, Response<ApiResponse<Meal>> response) {
                // Check if the response is successful and contains a valid body
                if (response.isSuccessful() && response.body() != null) {
                    mealCallback.onSuccess(response.body().meals); // Pass the meal list to the callback on success
                } else {
                    mealCallback.onFailure("Failed to fetch meals by country: Response was unsuccessful or empty."); // Handle failure case
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Meal>> call, Throwable throwable) {
                mealCallback.onFailure("Failed to fetch meals by country: " + throwable.getMessage()); // Handle failure and pass the error message
            }
        });
    }

    @Override
    public void fetchMealCategories(NetworkCallback<Category> categoryCallback) {
        // Make an asynchronous network call to fetch meal categories
        apiServices.fetchMealCategories().enqueue(new Callback<ApiResponse<Category>>() {
            @Override
            public void onResponse(Call<ApiResponse<Category>> call, Response<ApiResponse<Category>> response) {
                // Check if the response is successful and contains a valid body
                if (response.isSuccessful() && response.body() != null) {
                    categoryCallback.onSuccess(response.body().categories); // Pass the category list to the callback on success
                } else {
                    categoryCallback.onFailure("Failed to fetch meal categories: Response was unsuccessful or empty."); // Handle failure case
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Category>> call, Throwable throwable) {
                categoryCallback.onFailure("Failed to fetch meal categories: " + throwable.getMessage()); // Handle failure and pass the error message
            }
        });
    }

    @Override
    public void fetchMealsByCategoryId(String categoryId, NetworkCallback<Meal> mealCallback) {
        // Make an asynchronous network call to fetch meals by category ID
        apiServices.fetchMealsByCategory(categoryId).enqueue(new Callback<ApiResponse<Meal>>() {
            @Override
            public void onResponse(Call<ApiResponse<Meal>> call, Response<ApiResponse<Meal>> response) {
                // Check if the response is successful and contains a valid body
                if (response.isSuccessful() && response.body() != null) {
                    mealCallback.onSuccess(response.body().meals); // Pass the meal list to the callback on success
                } else {
                    mealCallback.onFailure("Failed to fetch meals by category: Response was unsuccessful or empty."); // Handle failure case
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Meal>> call, Throwable throwable) {
                mealCallback.onFailure("Failed to fetch meals by category: " + throwable.getMessage()); // Handle failure and pass the error message
            }
        });
    }

    @Override
    public void fetchCountries(NetworkCallback<Country> countryCallback) {
        // Make an asynchronous network call to fetch meals by category ID
        apiServices.fetchCountries().enqueue(new Callback<ApiResponse<Country>>() {
            @Override
            public void onResponse(Call<ApiResponse<Country>> call, Response<ApiResponse<Country>> response) {
                // Check if the response is successful and contains a valid body
                if (response.isSuccessful() && response.body() != null) {
                    countryCallback.onSuccess(response.body().meals); // Pass the meal list to the callback on success
                } else {
                    countryCallback.onFailure("Failed to fetch countries: Response was unsuccessful or empty."); // Handle failure case
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Country>> call, Throwable throwable) {
                countryCallback.onFailure("Failed to fetch countries: " + throwable.getMessage()); // Handle failure and pass the error message
            }
        });
    }

    @Override
    public void fetchIngredients(NetworkCallback<Ingredients> ingredientCallback) {
        // Make an asynchronous network call to fetch meals by category ID
        apiServices.fetchIngredients().enqueue(new Callback<ApiResponse<Ingredients>>() {
            @Override
            public void onResponse(Call<ApiResponse<Ingredients>> call, Response<ApiResponse<Ingredients>> response) {
                // Check if the response is successful and contains a valid body
                if (response.isSuccessful() && response.body() != null) {
                    ingredientCallback.onSuccess(response.body().meals); // Pass the meal list to the callback on success
                } else {
                    ingredientCallback.onFailure("Failed to fetch ingredients: Response was unsuccessful or empty."); // Handle failure case
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Ingredients>> call, Throwable throwable) {
                ingredientCallback.onFailure("Failed to fetch ingredients: " + throwable.getMessage()); // Handle failure and pass the error message
            }
        });
    }
}
