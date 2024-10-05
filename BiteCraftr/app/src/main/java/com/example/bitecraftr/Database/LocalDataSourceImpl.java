package com.example.bitecraftr.Database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.bitecraftr.Model.Meal;
import com.example.bitecraftr.Model.ScheduledMeal;

import java.util.List;

/**
 * Implementation of the LocalDataSource interface for managing meal data in the local database.
 */
public class LocalDataSourceImpl implements LocalDataSource {

    // Singleton instance of LocalDataSourceImpl
    private static LocalDataSourceImpl instance = null;

    // LiveData object holding the list of meals loaded from the database
    private LiveData<List<Meal>> liveDataMeals;

    // Application context
    private Context appContext;

    // DAO for accessing meal data
    private AppDao appDao;

    /**
     * Private constructor to initialize the local data source.
     *
     * @param context The application context.
     */
    private LocalDataSourceImpl(Context context) {
        this.appContext = context; // Store the application context
        AppDatabase db = AppDatabase.getInstance(appContext.getApplicationContext());
        appDao = db.loadMealsDAO(); // Load the DAO for meal operations
        liveDataMeals = appDao.daoLoadFavouriteMeals(); // Load all favorite meals as LiveData
    }

    /**
     * Returns the singleton instance of LocalDataSourceImpl.
     *
     * @param context The application context.
     * @return The singleton instance.
     */
    public static LocalDataSourceImpl getInstance(Context context) {
        // Create the instance if it doesn't already exist
        if (instance == null) {
            instance = new LocalDataSourceImpl(context);
        }
        return instance; // Return the singleton instance
    }

    @Override
    public void insertFavouriteMeal(Meal meal) {
        // Insert the meal in a background thread to avoid blocking the UI
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDao.daoInsertFavouriteMeal(meal); // Insert the meal into the database
            }
        }).start();
    }

    @Override
    public void deleteFavouriteMeal(Meal meal) {
        // Delete the meal in a background thread to avoid blocking the UI
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDao.daoDeleteFavouriteMeal(meal); // Remove the meal from the database
            }
        }).start();
    }

    @Override
    public LiveData<List<Meal>> loadFavouriteMeals() {
        return liveDataMeals; // Return the LiveData object holding stored favorite meals
    }

    @Override
    public LiveData<List<ScheduledMeal>> loadScheduledMeals(String date) {
        // Load scheduled meals for the specified date from the database
        return appDao.daoLoadScheduledMeals(date);
    }

    @Override
    public void insertScheduledMeal(ScheduledMeal meal) {
        // Insert the scheduled meal in a background thread to avoid blocking the UI
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDao.daoInsertScheduledMeal(meal); // Insert the scheduled meal into the database
            }
        }).start();
    }

    @Override
    public void deleteScheduledMeal(ScheduledMeal meal) {
        // Delete the meal in a background thread to avoid blocking the UI
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDao.daoDeleteScheduledMeal(meal); // Remove the meal from the database
            }
        }).start();
    }
}
