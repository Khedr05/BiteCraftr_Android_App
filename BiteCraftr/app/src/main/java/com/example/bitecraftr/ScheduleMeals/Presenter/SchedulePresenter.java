package com.example.bitecraftr.ScheduleMeals.Presenter;

import androidx.lifecycle.LiveData; // Import LiveData to observe changes in the list of scheduled meals.
import com.example.bitecraftr.Model.ScheduledMeal; // Import the ScheduledMeal model that represents a meal.

import java.util.List; // Import the List class for handling a list of meals.

/**
 * SchedulePresenter interface defines the contract between the View (e.g., Fragment) and the Presenter.
 * It declares the methods that the View will use to interact with the Presenter.
 */
public interface SchedulePresenter {

    /**
     * This method is responsible for loading the list of scheduled meals for a specific date.
     * The method returns a LiveData object, which allows the View to observe changes to the meal list.
     *
     * @param mealDate The date for which to load the scheduled meals (formatted as a String).
     * @return LiveData object containing a list of ScheduledMeal objects.
     */
    public LiveData<List<ScheduledMeal>> loadScheduleMeals(String mealDate);

    /**
     * This method is responsible for deleting a specific scheduled meal from the list.
     * The View can call this method to remove a meal from the schedule.
     *
     * @param meal The ScheduledMeal object representing the meal to be deleted.
     */
    public void deleteScheduledMeal(ScheduledMeal meal);
}
