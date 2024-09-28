package com.example.flavor.db;

import androidx.lifecycle.LiveData;

import com.example.flavor.model.Meal;

import java.util.List;

public interface MealsLocalDataSource {
    void insert(Meal meal);
    void delete(Meal meal);
    LiveData<List<Meal>> getStoredData();
}
