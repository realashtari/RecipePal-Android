package com.mobileapp.recipepal;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateRecipeViewModel extends AndroidViewModel {
    private RecipeDao recipeDao;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public CreateRecipeViewModel(Application application) {
        super(application);

        // Initialize the dao using Room database
        AppDatabase appDatabase = AppDatabase.getDatabase(application);
        recipeDao = appDatabase.recipeDao();
    }

    public void addRecipe(String title, String ingredients, String instructions, String imageURL) {
        Recipe newRecipe = new Recipe();
        newRecipe.recipeName = title;
        newRecipe.Ingredients = ingredients;
        newRecipe.Instructions = instructions;
        newRecipe.ImageUrl = imageURL;

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                recipeDao.insertSingle(newRecipe);
            }
        });
    }
}
