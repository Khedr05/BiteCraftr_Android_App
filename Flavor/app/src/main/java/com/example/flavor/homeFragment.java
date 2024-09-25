package com.example.flavor;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flavor.home.presnter.HomePresnter;
import com.example.flavor.home.presnter.HomePresnterImpl;
import com.example.flavor.home.view.CategoriesAdapter;
import com.example.flavor.home.view.CategoriesInterface;
import com.example.flavor.home.view.HomeActivityInterface;
import com.example.flavor.home.view.HomeAdapter;
import com.example.flavor.model.Category;
import com.example.flavor.model.Meal;
import com.example.flavor.model.MealsRepositoryImpl;
import com.example.flavor.network.MealsRemoteDataSourceImpl;

import java.util.ArrayList;
import java.util.List;


public class homeFragment extends Fragment implements HomeActivityInterface, CategoriesInterface {

    public static final String TAG = "HomeActivity";
    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private CategoriesAdapter categoriesAdapter;
    private RecyclerView categoryRecyclerView;

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

        recyclerView.setHasFixedSize(true);
        linearLayout = new LinearLayoutManager(getActivity());
        homeAdapter = new HomeAdapter(getActivity(),new ArrayList<>());
        //homePresenter = new HomePresnterImpl((HomeActivityInterface) this, (MealsRepository) MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance()), (CategoriesInterface) this);
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(homeAdapter);

        /*categoryRecyclerView.setHasFixedSize(true);
        categoriesLinearLayout = new LinearLayoutManager(getActivity());
        categoriesAdapter = new CategoriesAdapter(getActivity(),new ArrayList<>());
        categoriesLinearLayout.setOrientation(RecyclerView.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(categoriesLinearLayout);
        categoryRecyclerView.setAdapter(categoriesAdapter);*/


        homePresenter = new HomePresnterImpl(this, MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance()),this);
        homePresenter.getRandomMeal();
        //homePresenter.getCategories();

        return view;
    }

    private void initUI(View v){

        recyclerView = v.findViewById(R.id.recView);
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