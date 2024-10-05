package com.example.bitecraftr.Explore.View;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.example.bitecraftr.Database.LocalDataSourceImpl;
import com.example.bitecraftr.Explore.Presenter.ExplorePresenter;
import com.example.bitecraftr.Explore.Presenter.ExplorePresenterImpl;
import com.example.bitecraftr.MealDetails.View.MealDetailsFragment;
import com.example.bitecraftr.Model.AppRepositoryImpl;
import com.example.bitecraftr.Model.Category;
import com.example.bitecraftr.Model.Country;
import com.example.bitecraftr.Model.Ingredients;
import com.example.bitecraftr.Model.Meal;
import com.example.bitecraftr.NavbarActivity;
import com.example.bitecraftr.Network.RemoteDataSourceImpl;
import com.example.bitecraftr.NetworkFragment;
import com.example.bitecraftr.R;

import java.util.ArrayList;
import java.util.List;

public class ExploreFragment extends Fragment implements ExploreView, CategoriesAdapterListener,
        MealsByCategoriesAdapterListener, CountryAdapterListener ,MealsByCountryAdapterListener ,
        IngredientsAdapterListener, MealsByIngredientsAdapterListener {

    // Presenter for handling data logic
    private ExplorePresenter explorePresenter;

    // ViewPager for displaying random meals
    private ViewPager2 randomViewPager;
    private ProgressBar randomprogressBar;
    private RandomMealPagerAdapter randomMealPagerAdapter;

    // UI elements for categories
    private ProgressBar progressBarCategory;
    private RecyclerView categoryRecyclerView;
    private CategoriesAdapter categoriesAdapter;

    // UI elements for meals by category
    private ProgressBar progressBarMealByCategory;
    private RecyclerView mealsByCategoryRecyclerView;
    private MealsByCategoriesAdapter mealsByCategoriesAdapter;

    // UI elements for meals by category
    private ProgressBar progressBarCountry;
    private RecyclerView countryRecyclerView;
    private CountryAdapter countryAdapter;

    // UI elements for meals by category
    private ProgressBar progressBarMealsByCountry;
    private RecyclerView mealsByCountryRecyclerView;
    private MealsByCountryAdapter mealsByCountryAdapter;

    // UI elements for meals by category
    private ProgressBar progressBarIngredients;
    private RecyclerView ingredientsRecyclerView;
    private IngredientsAdapter ingredientsAdapter;

    // UI elements for meals by category
    private ProgressBar progressBarMealsByIngredients;
    private RecyclerView mealsByIngredientsRecyclerView;
    private MealsByIngredientsAdapter mealsByIngredientsAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dashboard_activty, container, false);
        initUi(view); // Initialize UI components

        if (!isNetworkConnected()) {
            // Redirect to NoConnectionFragment
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, new NetworkFragment());
            transaction.addToBackStack(null);
            transaction.commit();
            return view;  // Return early to avoid fetching data
        }


        // Initialize the presenter with data sources
        explorePresenter = new ExplorePresenterImpl(this, AppRepositoryImpl.getInstance(RemoteDataSourceImpl.getInstance(), LocalDataSourceImpl.getInstance(getContext())));

        // Set up the ViewPager for random meals
        randomMealPagerAdapter = new RandomMealPagerAdapter(getActivity(), new ArrayList<>());
        randomViewPager.setAdapter(randomMealPagerAdapter);
        explorePresenter.exploreGetRandomMeal(); // Fetch random meals

        // Set up the RecyclerView for categories
        categoriesAdapter = new CategoriesAdapter(getActivity(), new ArrayList<>(), this);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        categoryRecyclerView.setAdapter(categoriesAdapter);
        explorePresenter.exploreGetCategories(); // Fetch categories

        // Set up the RecyclerView for meals by category
        mealsByCategoriesAdapter = new MealsByCategoriesAdapter(getActivity(), new ArrayList<>(), this);
        mealsByCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        mealsByCategoryRecyclerView.setAdapter(mealsByCategoriesAdapter);

        countryAdapter = new CountryAdapter(getActivity(), new ArrayList<>(), this);
        countryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        countryRecyclerView.setAdapter(countryAdapter);
        explorePresenter.exploreGetCountries();

        mealsByCountryAdapter = new MealsByCountryAdapter(getActivity(), new ArrayList<>(), this);
        mealsByCountryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        mealsByCountryRecyclerView.setAdapter(mealsByCountryAdapter);

        ingredientsAdapter = new IngredientsAdapter(getActivity(), new ArrayList<>(), this);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        ingredientsRecyclerView.setAdapter(ingredientsAdapter);
        explorePresenter.exploreGetIngredients();

        mealsByIngredientsAdapter = new MealsByIngredientsAdapter(getActivity(), new ArrayList<>(), this);
        mealsByIngredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        mealsByIngredientsRecyclerView.setAdapter(mealsByIngredientsAdapter);

        return view; // Return the inflated view
    }

    // Initialize UI components
    private void initUi(View v) {
        randomViewPager = v.findViewById(R.id.viewPagerRandom);
        randomprogressBar = v.findViewById(R.id.progressBarRandom);
        categoryRecyclerView = v.findViewById(R.id.recViewCategories);
        progressBarCategory = v.findViewById(R.id.progressBarCategories);
        progressBarMealByCategory = v.findViewById(R.id.progressBarMealCategory);
        mealsByCategoryRecyclerView = v.findViewById(R.id.recViewMealCategory);
        countryRecyclerView = v.findViewById(R.id.recViewCountry);
        progressBarCountry = v.findViewById(R.id.progressBarCountry);
        progressBarMealsByCountry = v.findViewById(R.id.progressBarMealsByCountry);
        mealsByCountryRecyclerView = v.findViewById(R.id.recViewMealsByCountry);
        progressBarIngredients = v.findViewById(R.id.progressBarIngredients);
        ingredientsRecyclerView = v.findViewById(R.id.recViewIngredients);
        progressBarMealsByIngredients = v.findViewById(R.id.progressBarMealsByIngredient);
        mealsByIngredientsRecyclerView = v.findViewById(R.id.recViewMealsByIngredient);
    }

    @Override
    public void exploreShowRandomMeals(List<Meal> meals) {
        // Update the random meals in the adapter and hide the progress bar
        randomMealPagerAdapter.setList(meals);
        randomMealPagerAdapter.notifyDataSetChanged();
        // Hide the progress bar
        randomprogressBar.setVisibility(View.GONE);
    }

    @Override
    public void exploreShowCategories(List<Category> categories) {
        // Update the categories in the adapter and hide the progress bar
        categoriesAdapter.setList(categories);
        categoriesAdapter.notifyDataSetChanged();
        // Hide the progress bar
        progressBarCategory.setVisibility(View.GONE);

        // If categories are available, fetch meals for the first category
        if (!categories.isEmpty()) {
            String firstCategoryId = categories.get(0).getStrCategory();
            explorePresenter.exploreGetMealsByCategory(firstCategoryId);
        } else {
            /* Do Nothing */
        }
    }

    @Override
    public void exploreShowMealsByCategory(List<Meal> meals) {
        // Update the meals by category in the adapter
        mealsByCategoriesAdapter.setList(meals);
        mealsByCategoriesAdapter.notifyDataSetChanged();

        // Scroll the RecyclerView to position 0
        mealsByCategoryRecyclerView.scrollToPosition(0);

        // Hide the progress bar
        progressBarMealByCategory.setVisibility(View.GONE);
    }

    @Override
    public void exploreShowErrorMsg(String error) {
        // Show an error dialog when an error occurs
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void exploreShowMealDetails(List<Meal> meal) {
        // Transition to the MealDetailsFragment when a meal is clicked
        MealDetailsFragment allMealDetailsFragment = MealDetailsFragment.getCurrentMeal(meal.get(0));
        ((NavbarActivity) getContext()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, allMealDetailsFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void exploreShowCountries(List<Country> countries) {
        // Update the categories in the adapter and hide the progress bar
        countryAdapter.setList(countries);
        countryAdapter.notifyDataSetChanged();
        // Hide the progress bar
        progressBarCountry.setVisibility(View.GONE);

        // If categories are available, fetch meals for the first category
        if (!countries.isEmpty()) {
            String firstCountryId = countries.get(0).getStrArea();
            explorePresenter.exploreGetMealsByCountry(firstCountryId);
        } else {
            /* Do Nothing */
        }
    }

    @Override
    public void exploreShowMealsByCountry(List<Meal> meals) {
        // Update the meals by category in the adapter
        mealsByCountryAdapter.setList(meals);
        mealsByCountryAdapter.notifyDataSetChanged();

        // Scroll the RecyclerView to position 0
        mealsByCountryRecyclerView.scrollToPosition(0);

        // Hide the progress bar
        progressBarMealsByCountry.setVisibility(View.GONE);
    }

    @Override
    public void exploreShowIngredients(List<Ingredients> ingredients) {

        ingredientsAdapter.setList(ingredients);
        ingredientsAdapter.notifyDataSetChanged();
        // Hide the progress bar
        progressBarIngredients.setVisibility(View.GONE);

        // If categories are available, fetch meals for the first category
        if (!ingredients.isEmpty()) {
            String firstIngredientId = ingredients.get(0).getStrIngredient();
            explorePresenter.exploreGetMealsByIngredients(firstIngredientId);
        }
    }

    @Override
    public void exploreShowMealsByIngredients(List<Meal> meals) {

        // Update the meals by category in the adapter
        mealsByIngredientsAdapter.setList(meals);
        mealsByIngredientsAdapter.notifyDataSetChanged();

        // Scroll the RecyclerView to position 0
        mealsByIngredientsRecyclerView.scrollToPosition(0);

        // Hide the progress bar
        progressBarMealsByIngredients.setVisibility(View.GONE);
    }


    @Override
    public void onCategoryClick(Category category) {
        // Fetch meals for the clicked category
        explorePresenter.exploreGetMealsByCategory(category.getStrCategory());
    }

    @Override
    public void onMealClick(String mealId) {
        // Transition to the MealDetailsFragment when a meal is clicked
        explorePresenter.exploreGetMealById(mealId);
    }


    ///////////////////////
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    @Override
    public void onCountryClick(Country country) {
        explorePresenter.exploreGetMealsByCountry(country.getStrArea());
    }

    @Override
    public void onCountryMealClick(String mealId) {
        explorePresenter.exploreGetMealById(mealId);
    }

    @Override
    public void onIngredientClick(Ingredients ingredient) {
        explorePresenter.exploreGetMealsByIngredients(ingredient.getStrIngredient());
    }

    @Override
    public void onIngredientMealClick(String mealId) {
        explorePresenter.exploreGetMealById(mealId);
    }
}
