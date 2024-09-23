package com.example.flavor.home.view;

import com.example.flavor.model.Meal;

import java.util.List;

public interface HomeActivityInterface {
    public void showData(List<Meal> meals);
    public void showErrMsg(String error);
}
