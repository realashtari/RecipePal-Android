package com.mobileapp.recipepal;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ViewModel Responsible for creating a single recipe.
 *
 * Inserting a single recipe must be done off of the main thread, so we use an executorService to
 * run the database operation on another thread.
 */
public class CreateRecipeViewModel extends AndroidViewModel {
    private RecipeDao recipeDao;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public CreateRecipeViewModel(Application application) {
        super(application);

        // Initialize the dao using Room database
        AppDatabase appDatabase = AppDatabase.getDatabase(application);
        recipeDao = appDatabase.recipeDao();
    }

    /**
     * This method allows us to add a single recipe to the database. A unique uid is given to the
     * recipe by the database.
     * @param title the name of the recipe
     * @param ingredients the ingredients of the recipe
     * @param instructions the instructions of the recipe
     * @param imageURL the url that we use to dynamically load the image from the internet
     */
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
