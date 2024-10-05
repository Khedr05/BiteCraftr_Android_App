package com.example.bitecraftr.Search.View;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bitecraftr.Model.Meal;
import com.example.bitecraftr.R;

import java.util.List;

/**
 * Adapter class for handling the display of a list of meals in a RecyclerView.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private static final String TAG = "SearchAdapter";
    private final Context context; // Context for UI operations
    private List<Meal> mealList; // List of meals to be displayed
    private final OnSearchClickListner listener; // Listener for click events
    private boolean byId = false; // Field to track if the meal is clicked by ID

    /**
     * Constructor for SearchAdapter.
     *
     * @param context    The context where the adapter is being used.
     * @param categories The initial list of meals to display.
     * @param listener   The listener to handle meal click events.
     */
    public SearchAdapter(Context context, List<Meal> categories, OnSearchClickListner listener) {
        this.context = context;
        this.mealList = categories;
        this.listener = listener;
    }

    /**
     * Updates the list of meals displayed by the adapter.
     *
     * @param categories The new list of meals to display.
     */
    public void setList(List<Meal> categories) {
        this.mealList = categories;
    }

    /**
     * Getter for the `byId` field, indicating if the meal is clicked by ID.
     *
     * @return True if clicked by ID, false otherwise.
     */
    public boolean getById() {
        return byId;
    }

    /**
     * Setter for the `byId` field.
     *
     * @param byId New value for the `byId` field.
     */
    public void setById(boolean byId) {
        this.byId = byId;
    }

    /**
     * ViewHolder class to hold and recycle views for each meal.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img; // ImageView for the meal thumbnail
        private TextView txtView; // TextView for the meal title
        public View layout; // The layout containing the views

        public ViewHolder(View v) {
            super(v);
            img = v.findViewById(R.id.searchedMealImg);
            txtView = v.findViewById(R.id.searchedMealTitle);
            layout = v;
        }
    }

    /**
     * Inflates the view for each meal and creates a new ViewHolder.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.meal_of_search_row, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "===== onCreateViewHolder =====");
        return vh;
    }

    /**
     * Binds data from the meal object to the views in the ViewHolder.
     *
     * @param holder   The ViewHolder for the current meal.
     * @param position The position of the meal in the list.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal meal = mealList.get(position);

        // Use Glide to load meal thumbnail
        Glide.with(context).load(meal.getStrMealThumb())
                .apply(new RequestOptions().override(200, 200)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_foreground))
                .into(holder.img);

        // Set an OnClickListener for the meal image to handle click events
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onMealClickListner(meal.getIdMeal());
                byId = true; // Set byId to true when a meal is clicked by ID
            }
        });

        // Set the meal name in the TextView
        holder.txtView.setText(meal.getStrMeal());
    }

    /**
     * Returns the number of items in the meal list.
     *
     * @return The size of the meal list.
     */
    @Override
    public int getItemCount() {
        if (mealList == null) {
            return 0;
        }
        else{
            return mealList.size();
        }
    }
}
