package com.example.bitecraftr.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.bitecraftr.Model.Meal;
import com.example.bitecraftr.Model.ScheduledMeal;

import java.util.List;

/**
 * Data Access Object (DAO) interface for accessing meal-related data in the database.
 */
@Dao
public interface AppDao {

    /**
     * Retrieves favourite meals from the Meals.
     *
     * @return A LiveData list of meals.
     */
    @Query("SELECT * FROM Meals")
    LiveData<List<Meal>> daoLoadFavouriteMeals();

    /**
     * Inserts a new favourite meal into the Meals.
     *
     * @param meal The meal to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void daoInsertFavouriteMeal(Meal meal);

    /**
     * Deletes a specified favourite meal from the Meals.
     *
     * @param meal The meal to be deleted.
     */
    @Delete
    void daoDeleteFavouriteMeal(Meal meal);


    /**
     * Loads all scheduled meals for a specific date from the Scheduled_Meals table.
     *
     * @param date The date for which the scheduled meals should be retrieved.
     * @return A LiveData object containing a list of ScheduledMeal entities associated with the given date.
     */
    @Query("SELECT * FROM Scheduled_Meals WHERE date = :date")
    LiveData<List<ScheduledMeal>> daoLoadScheduledMeals(String date);

    /**
     * Inserts a new scheduled meal into the Scheduled_Meals table.
     * If a meal with the same ID already exists, it will be ignored.
     *
     * @param meal The ScheduledMeal object to be inserted into the database.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void daoInsertScheduledMeal(ScheduledMeal meal);


    /**
     * Deletes a specified scheduled meal from the Scheduled_Meals table.
     *
     * @param meal The ScheduledMeal object to be deleted from the database.
     */
    @Delete
    void daoDeleteScheduledMeal(ScheduledMeal meal);
}
