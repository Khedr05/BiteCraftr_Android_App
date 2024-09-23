package com.example.flavor.network;

import com.example.flavor.model.Meal;

import java.util.List;

public interface NetworkCallback {
    public void onSuccessResult(List<Meal> meals);
    public void onFailureResult(String errorMsg);
}
