package com.mobileapp.recipepal;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ViewModel Responsible for getting a single recipe and updating a single recipe.
 *
 * The getRecipeById is LiveData type, so it does not need to be run off the main thread.
 *
 * However, update is not LiveData type, so it must be run on a separate thread from the main
 * thread.
 */
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

    /**
     * Method to get the recipe by its id as LiveData
     * @return a single recipe
     * @see LiveData<Recipe>
     */
    public LiveData<Recipe> getRecipeById() {
        return this.recipeDao.loadById(this.recipeId);
    }

    /**
     * This method allows us to update a single recipe in the database.
     * The Recipe's uid is already known by the class because it is an argument in the constructor.
     * @param title the name of the recipe
     * @param ingredients the ingredients of the recipe
     * @param instructions the instructions of the recipe
     * @param imageUrl the url that we use to dynamically load the image from the internet
     */
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
