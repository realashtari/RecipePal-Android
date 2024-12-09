package com.mobileapp.recipepal;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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
