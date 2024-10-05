package com.example.bitecraftr.Search.View;

import com.example.bitecraftr.Model.Meal;

import java.util.List;

/**
 * SearchView interface defines the methods that any class implementing this interface
 * must provide to display search results and handle error messages in the search feature.
 */
public interface SearchView {

    /**
     * Displays a list of meals retrieved from the search.
     *
     * @param meal A list of Meal objects to be displayed.
     */
    public void showData(List<Meal> meal);

    /**
     * Displays an error message when there is an issue with the search or data retrieval.
     *
     * @param error A string containing the error message to be displayed.
     */
    public void showErrorMsg(String error);
}
