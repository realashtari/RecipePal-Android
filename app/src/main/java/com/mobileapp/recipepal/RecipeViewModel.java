package com.mobileapp.recipepal;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RecipeViewModel extends AndroidViewModel {

    private RecipeDao recipeDao;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private List<Recipe> recipeList;


    public RecipeViewModel(Application application) {
        super(application);

        // Initialize the dao using Room database
        AppDatabase appDatabase = AppDatabase.getDatabase(application);
        recipeDao = appDatabase.recipeDao();
    }

    // Method to get the recipe by its id as LiveData
    public LiveData<Recipe> getRecipeById(int recipeId) { return recipeDao.loadById(recipeId); }

    // Method to get all recipes as LiveData
    public LiveData<List<Recipe>> getAllRecipes() {return recipeDao.getAll();}

    // Method to delete a recipe
    public void deleteSingle(Recipe recipe) {
        recipeDao.deleteSingle(recipe);
    }



}
