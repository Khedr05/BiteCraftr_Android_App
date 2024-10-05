package com.example.bitecraftr.FavouriteMeals.View;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bitecraftr.Database.LocalDataSourceImpl;
import com.example.bitecraftr.FavouriteMeals.Presenter.FavMealsPresenter;
import com.example.bitecraftr.FavouriteMeals.Presenter.FavMealsPresnterImpl;
import com.example.bitecraftr.Model.AppRepositoryImpl;
import com.example.bitecraftr.Model.Meal;
import com.example.bitecraftr.Network.RemoteDataSourceImpl;
import com.example.bitecraftr.R;

import java.util.List;

// Fragment that displays the favorite meals list and handles user interactions with the list
public class FavoritesFragment extends Fragment implements FavMealsView, OnFavMealsAdapterListener {

    // LiveData holds the list of favorite meals
    private LiveData<List<Meal>> mealList;

    // RecyclerView to display the list of meals
    private RecyclerView recyclerView;
    private FavMealsAdapter mealAdapter;
    private FavMealsPresenter mealPresenter;
    private LinearLayoutManager layoutManager;

    // Called when the fragment is first created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Called when the fragment's view is being created
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        // Initialize the RecyclerView
        recyclerView = view.findViewById(R.id.favRecView);
        recyclerView.setHasFixedSize(true); // Improves performance if size of the RecyclerView doesn't change

        // Set up the layout manager for the RecyclerView
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        // Initialize the presenter with the view and repository (which has local and remote data sources)
        mealPresenter = new FavMealsPresnterImpl(this, AppRepositoryImpl.getInstance(RemoteDataSourceImpl.getInstance(),
                LocalDataSourceImpl.getInstance(getContext())));

        // Fetch the list of favorite meals from the presenter
        mealList = mealPresenter.loadFavMeals();

        // Initialize the adapter with the context, meal list, and listener
        mealAdapter = new FavMealsAdapter(getContext(), mealList.getValue(), this);
        recyclerView.setAdapter(mealAdapter);

        // Observe the LiveData for changes in the meal list and update the UI accordingly
        mealList.observe(getViewLifecycleOwner(), new Observer<List<Meal>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(List<Meal> meals) {
                if (meals != null) {
                    // Update the view with the new list of meals
                    favShowMeals(meals);
                } else {
                    // Show an error message if no meals were found
                    favShowErrorMsg("No Meals Found");
                }
            }
        });

        return view; // Return the inflated view
    }

    // Called when the user clicks the remove button on a meal item
    @Override
    public void onFavRemoveMealClick(Meal meal) {
        // Remove the meal using the presenter and show a toast message
        mealPresenter.removeFavMeal(meal);
        Toast.makeText(getContext(), "Meal Removed", Toast.LENGTH_SHORT).show();
    }

    // Method to update the view with the list of favorite meals
    @Override
    public void favShowMeals(List<Meal> meal) {
        mealAdapter.setMeals(meal); // Set the meals in the adapter
        mealAdapter.notifyDataSetChanged(); // Notify the adapter that data has changed
    }

    // Method to show an error message (e.g., if no meals are found)
    @Override
    public void favShowErrorMsg(String errorMsg) {
        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show(); // Show a toast with the error message
    }
}
