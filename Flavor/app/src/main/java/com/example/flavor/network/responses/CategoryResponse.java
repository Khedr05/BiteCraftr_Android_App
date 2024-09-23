package com.example.flavor.network.responses;

import com.example.flavor.model.Category;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse {

    @SerializedName("Category")
    private List<Category> CategoryList;

    public CategoryResponse(List<Category> categories){
        this.CategoryList =  categories;
    }

    public List<Category> getCategories() {
        return CategoryList;
    }
    public void setMeals(List<Category> categories) {
        this.CategoryList = categories;
    }

}
