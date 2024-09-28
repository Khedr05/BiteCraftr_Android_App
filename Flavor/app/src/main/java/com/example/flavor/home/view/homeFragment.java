package com.example.flavor.home.view;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.flavor.R;
import com.example.flavor.db.MealsLocalDataSourceImpl;
import com.example.flavor.home.presnter.HomePresnter;
import com.example.flavor.home.presnter.HomePresnterImpl;
import com.example.flavor.mealsDetails.view.OnClickAddToFavListener;
import com.example.flavor.model.Category;
import com.example.flavor.model.Meal;
import com.example.flavor.model.MealsRepositoryImpl;
import com.example.flavor.network.MealsRemoteDataSourceImpl;

import java.util.ArrayList;
import java.util.List;


public class homeFragment extends Fragment implements HomeActivityInterface, OnClickAddToFavListener {

    public static final String TAG = "HomeActivity";
    private RecyclerView categoryRecyclerView;
    private HomePagerAdapter homePagerAdapter;
    private CategoriesAdapter categoriesAdapter;

    private ViewPager2 viewPager;
    private ProgressBar progressBarRandom;
    private ProgressBar progressBarCategory;
    private ProgressBar progressBarMealByCategory;

    HomePresnter homePresenter;
    LinearLayoutManager linearLayout;
    LinearLayoutManager categoriesLinearLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dashboard_activty, container, false);

        initUI(view);

        homePresenter = new HomePresnterImpl(this, MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance() , MealsLocalDataSourceImpl.getInstance(getContext())));

        homePagerAdapter = new HomePagerAdapter(getActivity(), new ArrayList<>());
        viewPager.setAdapter(homePagerAdapter);

        categoryRecyclerView.setHasFixedSize(true);
        categoriesLinearLayout = new LinearLayoutManager(getActivity());
        categoriesAdapter = new CategoriesAdapter(getActivity(),new ArrayList<>());
        categoriesLinearLayout.setOrientation(RecyclerView.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(categoriesLinearLayout);
        categoryRecyclerView.setAdapter(categoriesAdapter);


        homePresenter.getRandomMeal();
        homePresenter.getCategories();

        return view;
    }

    private void initUI(View v){

        viewPager = v.findViewById(R.id.viewPagerRandom);
        categoryRecyclerView = v.findViewById(R.id.recViewCategories);
        progressBarRandom = v.findViewById(R.id.progressBarRandom);
        progressBarCategory = v.findViewById(R.id.progressBarCategories);
        progressBarMealByCategory = v.findViewById(R.id.progressBarMealCategory);
    }


    @Override
    public void showData(List<Meal> meals) {
        homePagerAdapter.setList(meals);
        homePagerAdapter.notifyDataSetChanged();
        progressBarRandom.setVisibility(View.GONE);
    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showCategories(List<Category> categories) {
        categoriesAdapter.setList(categories);
        categoriesAdapter.notifyDataSetChanged();
        progressBarCategory.setVisibility(View.GONE);
    }

    @Override
    public void showCategoriesError(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}