package com.example.flavor.favMeals.view;

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

import com.example.flavor.R;
import com.example.flavor.db.MealsLocalDataSourceImpl;
import com.example.flavor.favMeals.presnter.FavMealsPresnterImpl;
import com.example.flavor.model.Meal;
import com.example.flavor.model.MealsRepositoryImpl;
import com.example.flavor.network.MealsRemoteDataSourceImpl;

import java.util.List;


public class FavMealsFragment extends Fragment implements FavMealsInterface,OnFavClickRemoveListener {

    private LiveData<List<Meal>> mealList;
    private RecyclerView recyclerView;
    private FavMealsAdapter mealAdapter;
    private FavMealsPresnterImpl mealPresenter;
    LinearLayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fav, container, false);
        recyclerView = view.findViewById(R.id.favRecView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        mealPresenter = new FavMealsPresnterImpl(this, MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance() , MealsLocalDataSourceImpl.getInstance(getContext())));
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mealList = mealPresenter.loadFavMeals();

        mealAdapter = new FavMealsAdapter(getContext(), mealList.getValue(), this);
        recyclerView.setAdapter(mealAdapter);

        mealList.observe(getViewLifecycleOwner(), new Observer<List<Meal>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(List<Meal> meals) {
                if (meals != null) {
                    showMeal(meals);
                }
                else {
                    showErrMsg("No Product Found");
                }
            }
        });
        return view;
    }


    @Override
    public void showMeal(List<Meal> meal) {
        mealAdapter.setMeals(meal);
        mealAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String errorMsg) {
        Toast.makeText(getContext(),errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFavMealClick(Meal meal) {
        mealPresenter.removeMeal(meal);
        Toast.makeText(getContext(),"Meal Removed", Toast.LENGTH_SHORT).show();
    }
}