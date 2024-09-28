package com.example.flavor.favMeals.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.flavor.R;
import com.example.flavor.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class FavMealsAdapter extends RecyclerView.Adapter<FavMealsAdapter.ViewHolder>{

    private final Context context;
    private List<Meal> mealList;
    private static final String TAG = "FavMealsAdapter";
    private OnFavClickRemoveListener listener;


    public FavMealsAdapter(Context _context, List<Meal> myDataset, OnFavClickRemoveListener listener) {
        this.context = _context;
        this.mealList = myDataset != null ? myDataset : new ArrayList<>();
        this.listener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView titleTxtView;
        public Button removeBtn;
        public ConstraintLayout constraintLayout;
        public View layout;


        public ViewHolder(View v) {
            super(v);
            layout = v;
            img = v.findViewById(R.id.img);
            titleTxtView = v.findViewById(R.id.textView);
            removeBtn = v.findViewById(R.id.rmBtn);
            constraintLayout = v.findViewById(R.id.main);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.fav_meals_page, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "===== onCreateViewHolder =====");
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Meal meal = mealList.get(position);
        Glide.with(context).load(meal.getStrMealThumb())
                .apply(new RequestOptions().override(200, 200)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground))
                .into(holder.img);
        holder.titleTxtView.setText(meal.getStrMeal());

        holder.removeBtn.setOnClickListener(view -> listener.onFavMealClick(meal));

        holder.layout.setOnClickListener(view ->
                Toast.makeText(context, meal.getStrMeal(), Toast.LENGTH_SHORT).show()
        );

        Log.i(TAG, "***** onBindViewHolder **************");
    }


    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public void setMeals(List<Meal> meal) {
        this.mealList = meal != null ? meal : new ArrayList<>();
        notifyDataSetChanged();
    }
}
