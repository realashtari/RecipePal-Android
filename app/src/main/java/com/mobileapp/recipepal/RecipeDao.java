package com.mobileapp.recipepal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Recipe Data Access Object (DAO), helps us easily perform database operations.
 *
 * We have the ability to Create (single recipe), read (all recipes and single recipe),
 *  update (single recipe), delete (single recipe)
 */
@Dao
public interface RecipeDao {
    @Query("SELECT * FROM recipe")
    LiveData<List<Recipe>> getAll();

    @Query("SELECT * FROM recipe")
    List<Recipe> getAllSync();

    @Query("SELECT * FROM recipe WHERE uid = :recipeId")
    LiveData<Recipe> loadById(int recipeId);

    @Insert
    long insertSingle(Recipe recipe);

    @Delete
    void deleteSingle(Recipe recipe);

    @Update
    void update(Recipe recipe);
}
