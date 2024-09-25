package com.example.flavor.home.presnter;

import com.example.flavor.home.view.CategoriesInterface;
import com.example.flavor.home.view.HomeActivityInterface;
import com.example.flavor.model.Category;
import com.example.flavor.model.MealsRepository;
import com.example.flavor.network.NetworkCallback;
import com.example.flavor.model.Meal;

import java.util.List;

public class HomePresnterImpl implements HomePresnter{

    private HomeActivityInterface _Randomview;
    private MealsRepository _repo;
    private CategoriesInterface _categoryView;

    public HomePresnterImpl(HomeActivityInterface Randomview , MealsRepository repo ,CategoriesInterface categoryView){
        this._Randomview = Randomview;
        this._categoryView = categoryView;
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
            _Randomview.showData(pojo);
        }

        @Override
        public void onFailureResult(String errorMsg) {
            _Randomview.showErrMsg(errorMsg);
        }
    }

    // Inner class for handling category meal callback
    private class CategoryMealCallback implements NetworkCallback<Category> {
        @Override
        public void onSuccessResult(List<Category> pojo) {
            _categoryView.showCategories(pojo);
        }

        @Override
        public void onFailureResult(String errorMsg) {
            _categoryView.showCategoriesError(errorMsg);
        }
    }
}

