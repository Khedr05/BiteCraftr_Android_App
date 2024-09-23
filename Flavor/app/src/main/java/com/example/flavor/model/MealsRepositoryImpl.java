package com.example.flavor.model;

import com.example.flavor.network.MealsRemoteDataSource;
import com.example.flavor.network.NetworkCallback;

public class MealsRepositoryImpl implements MealsRepository{

    MealsRemoteDataSource remoteSource;
    private static MealsRepositoryImpl repo = null;

    private MealsRepositoryImpl (MealsRemoteDataSource remoteSource)
    {
        this.remoteSource = remoteSource;
    }

    public static MealsRepositoryImpl getInstance(MealsRemoteDataSource remoteSource)
    {
        if(repo == null)
        {
            repo = new MealsRepositoryImpl(remoteSource);
        }
        return repo;
    }

    @Override
    public void getRandomMeal(NetworkCallback networkCallback) {
            remoteSource.makeNetworkCallRandomMeal(networkCallback);
    }
}
