package com.mobileapp.recipepal;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class RecipeViewModel extends AndroidViewModel {

    private RecipeDao recipeDao;

    public RecipeViewModel(Application application) {
        super(application);

        // Initialize the dao using Room database
        AppDatabase appDatabase = AppDatabase.getDatabase(application);
        recipeDao = appDatabase.recipeDao();
    }

    // Method to get the recipe by its id as LiveData
    public LiveData<Recipe> getRecipeById(int recipeId) {
        return recipeDao.loadById(recipeId);
    }



}
