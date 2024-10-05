package com.example.bitecraftr.ScheduleMeals.View;

// Required imports
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bitecraftr.Database.LocalDataSourceImpl;
import com.example.bitecraftr.Model.AppRepositoryImpl;
import com.example.bitecraftr.Model.ScheduledMeal;
import com.example.bitecraftr.Network.RemoteDataSourceImpl;
import com.example.bitecraftr.R;
import com.example.bitecraftr.ScheduleMeals.Presenter.SchedulePresenter;
import com.example.bitecraftr.ScheduleMeals.Presenter.SchedulePresenterImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

// Fragment class to manage scheduling meals
public class ScheduleFragment extends Fragment implements OnScheduleMealsAdapterListener {

    // Presenter to manage the scheduling logic
    private SchedulePresenter schedulePresenter;

    // Adapter for the RecyclerView to display scheduled meals
    private ScheduleMealsAdapter scheduleMealsAdapter;

    // LiveData to observe the list of scheduled meals
    private LiveData<List<ScheduledMeal>> scheduleMeals;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize the presenter with a repository that combines local and remote data sources
        schedulePresenter = new SchedulePresenterImpl(AppRepositoryImpl.getInstance(RemoteDataSourceImpl.getInstance(),
                LocalDataSourceImpl.getInstance(getContext())));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        // Reference to the DatePicker and RecyclerView
        DatePicker datePicker = view.findViewById(R.id.datePicker2);
        RecyclerView recyclerViewFav = view.findViewById(R.id.recPlaned);

        // Set up the RecyclerView with an adapter
        setupRecyclerView(recyclerViewFav);

        // Get the current date
        Calendar calendar = Calendar.getInstance();

        // Initialize the DatePicker with the current date and set a listener for date changes
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                (view1, year, monthOfYear, dayOfMonth) -> {
                    // Format the selected date as a string
                    String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;

                    // Load the scheduled meals for the selected date
                    loadScheduledMeals(selectedDate);

                    // Notify other fragments about the date change if needed
                    Bundle result = new Bundle();
                    result.putString("selectedDate", selectedDate);
                    getParentFragmentManager().setFragmentResult("requestKey", result);
                });

        // Return the view for the fragment
        return view;
    }

    // Method to set up the RecyclerView with an adapter
    private void setupRecyclerView(RecyclerView recyclerView) {
        // Initialize the adapter with an empty list and set it to the RecyclerView
        scheduleMealsAdapter = new ScheduleMealsAdapter(getContext(), new ArrayList<>(), this);
        recyclerView.setAdapter(scheduleMealsAdapter);
        // Set the layout manager for the RecyclerView to arrange items vertically
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    // Method to load scheduled meals for the selected date
    private void loadScheduledMeals(String selectedDate) {
        // Load the meals using the presenter and observe changes
        scheduleMeals = schedulePresenter.loadScheduleMeals(selectedDate);
        scheduleMeals.observe(getViewLifecycleOwner(), new Observer<List<ScheduledMeal>>() {
            @Override
            public void onChanged(List<ScheduledMeal> foodPlans) {
                // Update the adapter with the new list of scheduled meals
                scheduleMealsAdapter.setList(foodPlans);
                scheduleMealsAdapter.notifyDataSetChanged();
            }
        });
    }

    // Method to handle meal removal when the user clicks to remove a meal
    @Override
    public void onRemoveMealClick(ScheduledMeal meal) {
        // Call the presenter to delete the scheduled meal
        schedulePresenter.deleteScheduledMeal(meal);
    }
}
