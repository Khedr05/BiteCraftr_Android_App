package com.example.flavor.model;

import androidx.lifecycle.LiveData;

import com.example.flavor.db.MealsLocalDataSource;
import com.example.flavor.db.MealsLocalDataSourceImpl;
import com.example.flavor.network.MealsRemoteDataSource;
import com.example.flavor.network.MealsRemoteDataSourceImpl;
import com.example.flavor.network.NetworkCallback;

import java.util.List;

public class MealsRepositoryImpl implements MealsRepository{

    MealsRemoteDataSource remoteSource;
    MealsLocalDataSourceImpl localSource;
    private static MealsRepositoryImpl repo = null;

    private MealsRepositoryImpl (MealsRemoteDataSource mealsRemoteDataSource , MealsLocalDataSourceImpl mealsLocalDataSource)
    {
        this.remoteSource = mealsRemoteDataSource;
        this.localSource = mealsLocalDataSource;
    }

    public static MealsRepositoryImpl getInstance(MealsRemoteDataSource mealsRemoteDataSource , MealsLocalDataSourceImpl mealsLocalDataSource)
    {
        if(repo == null)
        {
            repo = new MealsRepositoryImpl(mealsRemoteDataSource,mealsLocalDataSource);
        }
        return repo;
    }

    @Override
    public void getRandomMeal(NetworkCallback<Meal> networkCallback) {
            remoteSource.makeNetworkCallRandomMeal(networkCallback);
    }

    @Override
    public void getCategories(NetworkCallback<Category> networkCallback) {
        remoteSource.makeNetworkCallGetCategory(networkCallback);
    }

    @Override
    public LiveData<List<Meal>> getStoredMeals() {
        return localSource.getStoredData();
    }

    @Override
    public void insertMeal(Meal meal) {
        localSource.insert(meal);
    }

    @Override
    public void deleteMeal(Meal meal) {
        localSource.delete(meal);
    }
}
