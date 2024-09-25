package com.example.flavor.home.view;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.flavor.R;
import com.example.flavor.MealDetailsFragment;
import com.example.flavor.model.Meal;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private static final String TAG = "RecyclerView";
    private final Context context;
    private List<Meal> values;

    public HomeAdapter(Context context, List<Meal> values) {
        this.context = context;
        this.values = values;
    }

    public void setList(List<Meal> meals){
        this.values = meals;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView txtView;
        public ConstraintLayout constraintLayout;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            img = v.findViewById(R.id.img);
            txtView = v.findViewById(R.id.textView);
            constraintLayout = v.findViewById(R.id.main);
        }
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.test, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "===== onCreateViewHolder =====");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(values.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(200,200)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground))
                .into(holder.img);
        holder.txtView.setText(values.get(position).getStrMeal());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MealDetailsFragment allMealDetailsFragment = MealDetailsFragment.getCurrentMeal(values.get(position));

                ((HomeActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,allMealDetailsFragment)
                        .addToBackStack(null).commit();
            }
        });

    }

    @Override
    public int getItemCount() {return values.size();}
}
