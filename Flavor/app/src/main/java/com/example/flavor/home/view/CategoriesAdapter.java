//package com.example.flavor.home.view;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.constraintlayout.widget.ConstraintLayout;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.RequestOptions;
//import com.example.flavor.R;
//import com.example.flavor.model.Category;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder>{
//
//    private static final String TAG = "CategoriesAdapter";
//    private final Context context;
//    private List<Category> categoryList;
//
//    // Constructor
//    public CategoriesAdapter(Context context, List<Category> categories) {
//        this.context = context;
//        // Ensure the category list is never null
//        this.categoryList = categories != null ? categories : new ArrayList<>();
//    }
//
//    // Method to set a new list and notify the adapter
//    public void setList(List<Category> categories) {
//        this.categoryList = categories != null ? categories : new ArrayList<>();
//        notifyDataSetChanged(); // Notify the adapter of data change
//    }
//
//    // ViewHolder class
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        private final ImageView img;
//        private final TextView txtView;
//        public ConstraintLayout constraintLayout;
//        public View layout;
//
//        public ViewHolder(View v) {
//            super(v);
//            layout = v;
//            img = v.findViewById(R.id.img);
//            txtView = v.findViewById(R.id.textView);
//            constraintLayout = v.findViewById(R.id.main);
//        }
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View v = inflater.inflate(R.layout.test, parent, false);
//        ViewHolder vh = new ViewHolder(v);
//        Log.i(TAG, "===== onCreateViewHolder =====");
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        // Ensure categoryList is not empty and position is valid
//        if (categoryList != null && !categoryList.isEmpty() && position < categoryList.size()) {
//            Category category = categoryList.get(position);
//            Glide.with(context)
//                    .load(category.getStrCategoryThumb())
//                    .apply(new RequestOptions().override(200, 200)
//                            .placeholder(R.drawable.ic_launcher_background)
//                            .error(R.drawable.ic_launcher_foreground))
//                    .into(holder.img);
//            holder.txtView.setText(category.getStrCategory());
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        // Return 0 if categoryList is null to prevent NullPointerException
//        return categoryList != null ? categoryList.size() : 0;
//    }
//}

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
import com.example.flavor.model.Category;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder>{

    private static final String TAG = "CategoriesAdapter";
    private final Context context;
    private List<Category> categoryList;

    public CategoriesAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categoryList = categories;
    }

    public void setList(List<Category> categories){ this.categoryList = categories; }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView txtView;
        public ConstraintLayout constraintLayout;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            img = v.findViewById(R.id.catImage);
            txtView = v.findViewById(R.id.catTitle);
            constraintLayout = v.findViewById(R.id.main);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.view_holder_category, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "===== onCreateViewHolder =====");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(categoryList.get(position).getStrCategoryThumb())
                .apply(new RequestOptions().override(200,200)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground))
                .into(holder.img);
        holder.txtView.setText(categoryList.get(position).getStrCategory());
    }

    @Override
    public int getItemCount() {return categoryList.size();}





}
