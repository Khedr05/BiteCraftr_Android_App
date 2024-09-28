package com.example.flavor.home.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.flavor.HomeActivity;
import com.example.flavor.R;
import com.example.flavor.mealsDetails.view.MealDetailsFragment;
import com.example.flavor.model.Meal;
import java.util.List;

public class HomePagerAdapter extends RecyclerView.Adapter<HomePagerAdapter.ViewHolder> {

    private final Context context;
    private List<Meal> meals;

    public HomePagerAdapter(Context context, List<Meal> meals) {
        this.context = context;
        this.meals = meals;
    }

    public void setList(List<Meal> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_image_container, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(meals.get(position).getStrMealThumb())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground))
                .into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MealDetailsFragment allMealDetailsFragment = MealDetailsFragment.getCurrentMeal(meals.get(position));

                ((HomeActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,allMealDetailsFragment)
                        .addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView);
        }
    }
}
