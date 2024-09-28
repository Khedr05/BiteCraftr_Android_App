package com.example.flavor.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.flavor.model.Meal;

import java.util.List;

public class MealsLocalDataSourceImpl {
    private static MealsLocalDataSourceImpl repo = null;
    private LiveData<List<Meal>> storedMeal;
    private Context context;
    private MealsDAO mealDAO;

    private MealsLocalDataSourceImpl(Context _context){
        this.context = _context;
        AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
        mealDAO = db.getMealDAO();
        storedMeal = mealDAO.getAllMeals();
    }

    public static MealsLocalDataSourceImpl getInstance(Context _context){
        if(repo == null){
            repo = new MealsLocalDataSourceImpl(_context);
        }
        return repo;
    }

    public LiveData<List<Meal>> getStoredData() {
        return storedMeal;
    }

    public void delete(Meal meal){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.deleteMeal(meal);
            }
        }).start();
    }

    public void insert(Meal meal){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.insertMeal(meal);
            }
        }).start();
    }

}
