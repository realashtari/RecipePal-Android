package com.mobileapp.recipepal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * A view model factory to allow us to pass 2 arguments to the UpdateRecipeViewModel constructor.
 */
public class UpdateRecipeViewModelFactory implements ViewModelProvider.Factory {
    private final int recipeId;
    private final Application application;

    public UpdateRecipeViewModelFactory(Application application, int recipeId) {
        this.recipeId = recipeId;
        this.application = application;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class <T> modelClass) {
        if (modelClass == UpdateRecipeViewModel.class) {
            return (T) new UpdateRecipeViewModel(application, recipeId);
        }
        return null;
    }
}
