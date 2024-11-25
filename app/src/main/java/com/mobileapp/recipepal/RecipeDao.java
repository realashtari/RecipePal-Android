package com.mobileapp.recipepal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RecipeDao {
    @Query("SELECT * FROM recipe")
    List<Recipe> getAll();

    @Query("SELECT * FROM recipe WHERE uid = :recipeId")
    Recipe loadById(int recipeId);

    @Insert
    void insertSingle(Recipe recipe);

    @Delete
    void deleteSingle(Recipe recipe);

    @Update
    void update(Recipe recipe);
}
