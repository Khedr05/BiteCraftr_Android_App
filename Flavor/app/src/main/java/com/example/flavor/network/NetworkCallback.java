package com.example.flavor.network;

import com.example.flavor.model.Meal;

import java.util.List;

public interface NetworkCallback<T>{
    public void onSuccessResult(List<T> meals);
    public void onFailureResult(String errorMsg);
}
