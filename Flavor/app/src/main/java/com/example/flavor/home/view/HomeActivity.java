package com.example.flavor.home.view;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flavor.R;
import com.example.flavor.home.presnter.HomePresnter;
import com.example.flavor.home.presnter.HomePresnterImpl;
import com.example.flavor.model.MealsRepositoryImpl;
import com.example.flavor.network.MealsRemoteDataSourceImpl;
import com.example.flavor.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeActivityInterface {

    public static final String TAG = "HomeActivity";
    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    HomePresnter homePresenter;
    LinearLayoutManager linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);


        initUI();

        recyclerView.setHasFixedSize(true);
        linearLayout = new LinearLayoutManager(this);
        homeAdapter = new HomeAdapter(HomeActivity.this,new ArrayList<>());
        homePresenter = new HomePresnterImpl(this,MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance()));
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(homeAdapter);
        homePresenter.getRandomMeal();


    }

    private void initUI(){
        recyclerView = findViewById(R.id.recView);
    }


    @Override
    public void showData(List<Meal> meals) {
        homeAdapter.setList(meals);
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}