package com.mobileapp.recipepal;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UpdateRecipeViewModel extends ViewModel {
    private RecipeDao recipeDao;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private int recipeId;

    public UpdateRecipeViewModel(Application application, int recipeId) {
        // Initialize the dao using Room database
        AppDatabase appDatabase = AppDatabase.getDatabase(application);
        recipeDao = appDatabase.recipeDao();
        this.recipeId = recipeId;
    }

    // Method to get the recipe by its id as LiveData
    public LiveData<Recipe> getRecipeById() {
        return this.recipeDao.loadById(this.recipeId);
    }

    public void update(String title, String instructions, String ingredients, String imageUrl) {
        Recipe recipe = new Recipe();

        recipe.uid = this.recipeId;
        recipe.recipeName = title;
        recipe.Instructions = instructions;
        recipe.Ingredients = ingredients;
        recipe.ImageUrl = imageUrl;

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                recipeDao.update(recipe);
            }
        });
    }
}
