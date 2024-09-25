package com.example.flavor.home.view;

import com.example.flavor.model.Category;

import java.util.List;

public interface CategoriesInterface {
    public void showCategories(List<Category> categories);
    public void showCategoriesError(String error);
}
