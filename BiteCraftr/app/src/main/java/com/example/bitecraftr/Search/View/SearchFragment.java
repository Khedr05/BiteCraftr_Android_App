package com.example.bitecraftr.Search.View;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.bitecraftr.Database.LocalDataSourceImpl;
import com.example.bitecraftr.MealDetails.View.MealDetailsFragment;
import com.example.bitecraftr.Model.AppRepositoryImpl;
import com.example.bitecraftr.Model.Meal;
import com.example.bitecraftr.Network.RemoteDataSourceImpl;
import com.example.bitecraftr.NetworkFragment;
import com.example.bitecraftr.Search.Presenter.SearchPresenter;
import com.example.bitecraftr.R;

import com.example.bitecraftr.Search.Presenter.SearchPresenterImpl;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * SearchFragment allows users to search for meals by different parameters
 * and display the search results in a RecyclerView. It also handles tab selection
 * and interacts with the SearchPresenter to fetch and display data.
 */
public class SearchFragment extends Fragment implements OnSearchClickListner, com.example.bitecraftr.Search.View.SearchView {

    // Adapter to handle displaying search results
    private SearchAdapter searchAdapter;
    // Presenter that handles fetching data from the repository
    private SearchPresenter searchPresenter;
    // RecyclerView for showing meals
    private RecyclerView recyclerView;

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    // Variable to track which tab is selected (0: Meal, 1: Country, etc.)
    int tap = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // No custom initialization needed here for this fragment
    }

    /**
     * Creates and inflates the view for the fragment, sets up the UI components, and configures the search functionality.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // Initialize RecyclerView from the layout
        recyclerView = view.findViewById(R.id.recView);
        // Initialize TabLayout for category-based search
        TabLayout tableLayout = view.findViewById(R.id.tabLayout);
        // SearchView for entering search queries
        SearchView searchView = view.findViewById(R.id.searchView);

        if (!isNetworkConnected()) {
            // Redirect to NoConnectionFragment
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, new NetworkFragment());
            transaction.addToBackStack(null);
            transaction.commit();
            return view;  // Return early to avoid fetching data
        }

        // Initialize SearchPresenter to handle data fetching
        searchPresenter = new SearchPresenterImpl(this, AppRepositoryImpl.getInstance(
                RemoteDataSourceImpl.getInstance(), LocalDataSourceImpl.getInstance(getContext())));

        // Initialize the searchAdapter with an empty list of meals
        searchAdapter = new SearchAdapter(getContext(), new ArrayList<Meal>(), this);
        // Configure the layout manager for the RecyclerView (vertical list)
        recyclerView.setLayoutManager(new GridLayoutManager(getContext() , 2));
        recyclerView.setAdapter(searchAdapter);

        // Handle TabLayout interactions (to select which category the user is searching by)
        tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Clear the RecyclerView data when the user switches tabs
                searchAdapter.setList(new ArrayList<Meal>());  // Set an empty list
                searchAdapter.notifyDataSetChanged();  // Notify the adapter that the data has changed
                // Clear the SearchView when a tab is selected
//                searchView.setQuery("", false);
//                searchView.clearFocus(); // Optionally clear the focus
                // Update the "tap" variable to keep track of which tab is selected
                tap = tab.getPosition();

                // Change the search hint in the SearchView depending on the selected tab
                switch (tap) {
                    case 0:
                        searchView.setQueryHint("Please Enter Meal name");
                        break;
                    case 1:
                        searchView.setQueryHint("Please Enter Country name");
                        break;
                    case 2:
                        searchView.setQueryHint("Please Enter Category name");
                        break;
                    case 3:
                        searchView.setQueryHint("Please Enter Ingredient name");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // No action needed when the tab is reselected
            }
        });

        // Handle the search query submission in the SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // Perform different searches based on the currently selected tab
                switch (tap) {
                    case 0:
                        searchPresenter.getMealByName(searchView.getQuery().toString());
                        break;
                    case 1:
                        searchPresenter.getMealsByCountry(searchView.getQuery().toString());
                        break;
                    case 2:
                        searchPresenter.getMealsByCategory(searchView.getQuery().toString());
                        break;
                    case 3:
                        searchPresenter.getMealsByIngredients(searchView.getQuery().toString());
                        break;
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                switch (tap) {
                    case 0:
                        searchPresenter.getMealByName(searchView.getQuery().toString());
                        break;
                }
                return false;
            }
        });

        return view;
    }

    /**
     * Handles clicks on a meal item from the search results by fetching details about that meal.
     */
    @Override
    public void onMealClickListner(String mealId) {
        // Fetch meal details based on the selected meal ID
        searchPresenter.getMealById(mealId);
    }

    /**
     * Displays an error message if something goes wrong during data fetching.
     */
    @Override
    public void showErrorMsg(String error) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, new NetworkFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
     * Displays the fetched meal data in the RecyclerView or opens the meal details fragment.
     */
    @Override
    public void showData(List<Meal> meals) {

        if(meals == null){
            Toast.makeText(getContext(), "No Meals Found", Toast.LENGTH_SHORT).show();
        }
        // Check if a specific meal was clicked (byId flag is true)
        else if (searchAdapter.getById()) {
            // Open the meal details fragment for the selected meal
            MealDetailsFragment mealFragment = MealDetailsFragment.getCurrentMeal(meals.get(0));
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, mealFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            // Reset the byId flag
            searchAdapter.setById(false);
        } else {
            // Update the searchAdapter with the fetched meals and notify the adapter to refresh the UI
                searchAdapter.setList(meals);
                searchAdapter.notifyDataSetChanged();
        }
    }
}
