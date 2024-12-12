package com.mobileapp.recipepal;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * View model responsible for loading a single recipe for the detailed recipe fragment.
 */
public class DetailedRecipeViewModel extends AndroidViewModel {
    private RecipeDao recipeDao;

    public DetailedRecipeViewModel(Application application) {
        super(application);
        // Initialize the dao using Room database
        AppDatabase appDatabase = AppDatabase.getDatabase(application);
        recipeDao = appDatabase.recipeDao();
    }
    // Method to get the recipe by its id as LiveData
    public LiveData<Recipe> getRecipeById(int recipeId) { return recipeDao.loadById(recipeId); }
}
