package com.example.bitecraftr.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.bitecraftr.Model.Meal;
import com.example.bitecraftr.Model.ScheduledMeal;

/**
 * Singleton class to manage the Room database instance for the application.
 */
@Database(entities = {Meal.class, ScheduledMeal.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    // Singleton instance of the AppDatabase
    private static AppDatabase instance = null;

    // Abstract method to get the DAO for meals
    protected abstract AppDao loadMealsDAO();

    /**
     * Returns the singleton instance of AppDatabase.
     *
     * @param context The application context.
     * @return The instance of AppDatabase.
     */
    public static synchronized AppDatabase getInstance(Context context) {
        // Create a new instance if it doesn't already exist
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "biteCraftrDb")
                    .build();
        }
        return instance; // Return the singleton instance
    }
}
