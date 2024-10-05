package com.example.bitecraftr.Explore.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bitecraftr.Model.Country;
import com.example.bitecraftr.R;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private final Context context;  // Context for inflating views
    private List<Country> countriesList;  // List of categories to display
    private CountryAdapterListener listener;  // Listener for category clicks

    // Constructor to initialize context, category list, and listener
    public CountryAdapter(Context context, List<Country> countries, CountryAdapterListener listener) {
        this.context = context;
        this.countriesList = countries;
        this.listener = listener;
    }

    // ViewHolder class to hold the views for each item in the RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;  // ImageView for the category image
        private TextView txtView;  // TextView for the category title
        public ConstraintLayout constraintLayout;  // Layout for the category item

        // Constructor to initialize views
        public ViewHolder(View v) {
            super(v);
            img = v.findViewById(R.id.countryImage);  // Initialize the ImageView
            txtView = v.findViewById(R.id.countryTitle);  // Initialize the TextView
            constraintLayout = v.findViewById(R.id.main);  // Initialize the ConstraintLayout
        }
    }

    @NonNull
    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        // Inflate the layout for each category item
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.explore_country_flags_row, recyclerView, false);
        return new CountryAdapter.ViewHolder(v);  // Return a new ViewHolder instance
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.ViewHolder holder, int position) {
        // Get the current category based on its position
        Country country = countriesList.get(position);

        Integer imageId = context.getResources().getIdentifier(country.getStrArea().toLowerCase(), "drawable", context.getPackageName());
        holder.img.setImageResource(imageId);

        holder.txtView.setText(country.getStrArea());  // Set the category title

        // Set a click listener for the category image
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Notify the listener when the category is clicked
                if (listener != null) {
                    listener.onCountryClick(country);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (countriesList == null) {
            return 0;  // Return 0 if the list is null
        }
        else{
            return countriesList.size();  // Return the total number of categories
        }
    }

    // Method to update the category list
    public void setList(List<Country> countries) {
        // Filter out countries with strArea equal to "Unknown"
        countries.removeIf(country -> "Unknown".equals(country.getStrArea()));

        this.countriesList = countries;  // Update the category list
        notifyDataSetChanged();  // Notify the adapter to refresh the view
    }

}
