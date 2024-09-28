package com.example.flavor.home.presnter;

import com.example.flavor.home.view.HomeActivityInterface;
import com.example.flavor.model.Category;
import com.example.flavor.model.MealsRepository;
import com.example.flavor.network.NetworkCallback;
import com.example.flavor.model.Meal;

import java.util.List;

public class HomePresnterImpl implements HomePresnter{

    private HomeActivityInterface _view;
    private MealsRepository _repo;


    public HomePresnterImpl(HomeActivityInterface Randomview , MealsRepository repo){
        this._view = Randomview;
        this._repo = repo;
    }


    @Override
    public void getRandomMeal() {
        _repo.getRandomMeal(new RandomMealCallback());
    }

    @Override
    public void getCategories() {
        _repo.getCategories(new CategoryMealCallback());
    }


    // Inner class for handling random meal callback
    private class RandomMealCallback implements NetworkCallback<Meal> {
        @Override
        public void onSuccessResult(List<Meal> pojo) {
            _view.showData(pojo);
        }

        @Override
        public void onFailureResult(String errorMsg) {
            _view.showErrMsg(errorMsg);
        }
    }

    // Inner class for handling category meal callback
    private class CategoryMealCallback implements NetworkCallback<Category> {
        @Override
        public void onSuccessResult(List<Category> pojo) {
            _view.showCategories(pojo);
        }

        @Override
        public void onFailureResult(String errorMsg) {
            _view.showCategoriesError(errorMsg);
        }
    }
}

