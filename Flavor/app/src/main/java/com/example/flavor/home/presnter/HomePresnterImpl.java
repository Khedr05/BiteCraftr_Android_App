package com.example.flavor.home.presnter;

import com.example.flavor.home.view.HomeActivityInterface;
import com.example.flavor.model.MealsRepository;
import com.example.flavor.network.NetworkCallback;
import com.example.flavor.model.Meal;

import java.util.List;

public class HomePresnterImpl implements HomePresnter, NetworkCallback {

    private HomeActivityInterface _view;
    private MealsRepository _repo;


    public HomePresnterImpl(HomeActivityInterface view , MealsRepository repo){
        this._view = view;
        this._repo = repo;
    }


    @Override
    public void getRandomMeal() {
        _repo.getRandomMeal(this);
    }

    @Override
    public void onSuccessResult(List<Meal> meals) {
        _view.showData(meals);
    }

    @Override
    public void onFailureResult(String errorMsg) {
        _view.showErrMsg(errorMsg);
    }
}
