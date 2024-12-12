package com.mobileapp.recipepal;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Class Responsible for initializing or getting the Database instance.
 *
 * If the INSTANCE is null, a new database will be initialized with the name "recipe-db", or else
 * it will return the database that already exists locally on the device.
 */
@Database(entities = {Recipe.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RecipeDao recipeDao();

    public static volatile AppDatabase INSTANCE;

    /**
     * @param context allows us to retrieve the applicationContext
     * @return the database instance
     * @see AppDatabase
     */
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "recipe-db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
