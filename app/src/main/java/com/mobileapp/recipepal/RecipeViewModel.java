package com.mobileapp.recipepal;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * ViewModel for the RecipeFragment.
 * Responsible for dynamically loading all of the recipes from the database.
 * The user can also delete a single recipe from the database.
 */
public class RecipeViewModel extends AndroidViewModel {

    private RecipeDao recipeDao;

    public RecipeViewModel(Application application) {
        super(application);

        // Initialize the dao using Room database
        AppDatabase appDatabase = AppDatabase.getDatabase(application);
        recipeDao = appDatabase.recipeDao();
    }

    /**
     * @return the list of recipes that will be shown dynamically to the user
     * @see LiveData<List<Recipe>>
     */
    public LiveData<List<Recipe>> getAllRecipes() {return recipeDao.getAll();}

    /**
     * Method to delete a recipe
     * @param recipe the recipe the user wishes to remove from the DB
     */
    public void deleteSingle(Recipe recipe) {
        recipeDao.deleteSingle(recipe);
    }
}
