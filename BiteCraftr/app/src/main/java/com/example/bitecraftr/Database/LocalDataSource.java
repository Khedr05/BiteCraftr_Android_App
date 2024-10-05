package com.example.bitecraftr.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;

import com.example.bitecraftr.Model.Meal;
import com.example.bitecraftr.Model.ScheduledMeal;

import java.util.List;

public interface LocalDataSource {

    /**
     * Inserts a favorite meal into the database.
     *
     * @param meal The Meal object to be inserted as a favorite.
     */
    void insertFavouriteMeal(Meal meal);

    /**
     * Deletes a favorite meal from the database.
     *
     * @param meal The Meal object to be deleted from the favorites.
     */
    void deleteFavouriteMeal(Meal meal);

    /**
     * Loads all favorite meals from the database.
     *
     * @return A LiveData object containing a list of favorite Meal entities.
     */
    LiveData<List<Meal>> loadFavouriteMeals();

    /**
     * Loads all scheduled meals for a specific date from the database.
     *
     * @param date The date for which the scheduled meals should be retrieved.
     * @return A LiveData object containing a list of ScheduledMeal entities associated with the given date.
     */
    LiveData<List<ScheduledMeal>> loadScheduledMeals(String date);

    /**
     * Inserts a scheduled meal into the database.
     *
     * @param meal The ScheduledMeal object to be inserted into the database.
     */
    void insertScheduledMeal(ScheduledMeal meal);

    /**
     * Deletes a specified scheduled meal from the Scheduled_Meals table.
     *
     * @param meal The ScheduledMeal object to be deleted from the database.
     */
    void deleteScheduledMeal(ScheduledMeal meal);
}
