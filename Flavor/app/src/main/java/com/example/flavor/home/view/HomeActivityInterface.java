package com.example.flavor.home.view;

import com.example.flavor.model.Category;
import com.example.flavor.model.Meal;

import java.util.List;

public interface HomeActivityInterface {
    public void showData(List<Meal> meals);
    public void showErrMsg(String error);
    public void showCategories(List<Category> categories);
    public void showCategoriesError(String error);
}
