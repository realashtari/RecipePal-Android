package com.mobileapp.recipepal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Recipe Schema in the Room database.
 */
@Entity
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "recipe_name")
    public String recipeName;

    @ColumnInfo(name = "ingredients")
    public String Ingredients;

    @ColumnInfo(name = "instructions")
    public String Instructions;

    @ColumnInfo(name = "image_url")
    public String ImageUrl;
}
