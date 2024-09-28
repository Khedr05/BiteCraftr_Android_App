package com.example.flavor.home.view;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private RecyclerView recyclerView;
    private RecyclerView categoryRecyclerView;
    private HomeAdapter homeAdapter;
    private CategoriesAdapter categoriesAdapter;

    HomePresnter homePresenter;
    LinearLayoutManager linearLayout;
    LinearLayoutManager categoriesLinearLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initUI(view);

        homePresenter = new HomePresnterImpl(this, MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance() , MealsLocalDataSourceImpl.getInstance(getContext())));

        recyclerView.setHasFixedSize(true);
        linearLayout = new LinearLayoutManager(getActivity());
        homeAdapter = new HomeAdapter(getActivity(),new ArrayList<>(),this);
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(homeAdapter);

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

        recyclerView = v.findViewById(R.id.recView);
        categoryRecyclerView = v.findViewById(R.id.recView2);
    }


    @Override
    public void showData(List<Meal> meals) {
        homeAdapter.setList(meals);
        homeAdapter.notifyDataSetChanged();
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
    }

    @Override
    public void showCategoriesError(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}