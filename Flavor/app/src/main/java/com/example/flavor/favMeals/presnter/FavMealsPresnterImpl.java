package com.example.flavor.favMeals.presnter;

import androidx.lifecycle.LiveData;

import com.example.flavor.favMeals.view.FavMealsInterface;
import com.example.flavor.model.Meal;
import com.example.flavor.model.MealsRepositoryImpl;

import java.util.List;

public class FavMealsPresnterImpl implements FavMealsPresnterInterface {

    private FavMealsInterface _view;
    private MealsRepositoryImpl _repo;

    public FavMealsPresnterImpl(FavMealsInterface view,MealsRepositoryImpl repository) {
        this._view = view;
        this._repo = repository;
    }

    @Override
    public LiveData<List<Meal>> loadFavMeals() {
        return _repo.getStoredMeals();
    }

    @Override
    public void removeMeal(Meal meal) {
        _repo.deleteMeal(meal);
    }
}
