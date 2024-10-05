package com.example.bitecraftr.ScheduleMeals.Presenter;

import androidx.lifecycle.LiveData;

import com.example.bitecraftr.Model.AppRepository;
import com.example.bitecraftr.Model.ScheduledMeal;

import java.util.List; // Import the List class for handling a list of meals.

/**
 * SchedulePresenterImpl is the implementation of the SchedulePresenter interface.
 * It serves as the Presenter in the MVP architecture, handling the business logic and communication
 * between the View and the AppRepository.
 */
public class SchedulePresenterImpl implements SchedulePresenter{

    private final AppRepository _repo; // Declare a reference to the AppRepository.

    /**
     * Constructor to initialize the SchedulePresenterImpl with an AppRepository instance.
     *
     * @param _repo An instance of AppRepository used to access data operations.
     */
    public SchedulePresenterImpl(AppRepository _repo) {
        this._repo = _repo; // Assign the provided repository to the local variable.
    }

    /**
     * Loads the scheduled meals for a specific date by calling the fetchScheduledMeals method
     * from the AppRepository.
     *
     * @param mealDate The date for which to load the scheduled meals (formatted as a String).
     * @return LiveData object containing a list of ScheduledMeal objects for the specified date.
     */
    @Override
    public LiveData<List<ScheduledMeal>> loadScheduleMeals(String mealDate) {
        return _repo.fetchScheduledMeals(mealDate); // Fetch the meals from the repository.
    }

    /**
     * Deletes a specific scheduled meal by calling the deleteScheduledMeal method from the AppRepository.
     *
     * @param meal The ScheduledMeal object representing the meal to be deleted.
     */
    @Override
    public void deleteScheduledMeal(ScheduledMeal meal) {
        _repo.deleteScheduledMeal(meal); // Delete the meal from the repository.
    }
}
