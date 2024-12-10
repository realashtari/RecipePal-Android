package com.mobileapp.recipepal;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.mobileapp.recipepal.databinding.FragmentDetailedRecipeBinding;

public class DetailedRecipeFragment extends Fragment {
    int recipeId; // Variable to store the recipe ID

    private FragmentDetailedRecipeBinding binding; // ViewBinding instance
    private RecipeViewModel recipeViewModel; // ViewModel instance

    // UI Views to display recipe details
    private TextView titleTextView;
    private ImageView recipeImageView;
    private TextView instructionsTextView;
    private TextView ingredientsTextView;

    // Called when the fragment's view is created
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Setting up ViewBinding
        binding = FragmentDetailedRecipeBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        // Initialize views using ViewBinding
        titleTextView = binding.textViewRecipeTitle;
        ingredientsTextView = binding.textViewIngredients;
        instructionsTextView = binding.textViewInstructions;
        recipeImageView = binding.imageView;

        // Get key for the recipe
        if (getArguments() != null) {
            recipeId = DetailedRecipeFragmentArgs.fromBundle(getArguments()).getRecipeId();
            Log.d("DetailedRecipeFragment", "Recipe ID: " + recipeId);
        }

        // Initialize the ViewModel
        recipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);

        // Observe LiveData from ViewModel for the recipe details
        recipeViewModel.getRecipeById(recipeId).observe(getViewLifecycleOwner(), new Observer<Recipe>() {
            @Override
            public void onChanged(Recipe recipe) {
                if (recipe != null) {
                    // Set the values to the UI
                    titleTextView.setText(recipe.recipeName);
                    instructionsTextView.setText(recipe.Instructions);
                    ingredientsTextView.setText(recipe.Ingredients);

                    // Get the image URL from the recipe object
                    String imageUrl = recipe.ImageUrl; // Access the ImageUrl field directly from recipe object

                    // Load the image into the ImageView using Glide
                    if (imageUrl != null && !imageUrl.isEmpty()) {
                        Glide.with(getContext())
                                .load(imageUrl)  // Load the image from the URL
                                .into(recipeImageView); // ImageView where you want to display the image
                    } else {
                        // Case where there is no image URL, default image used
                        Glide.with(getContext())
                                .load(R.drawable.default_food)
                                .override(600,400)
                                .into(recipeImageView);
                    }
                }
            }
        });

        // Handle the close button click event to navigate back
        binding.closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action =
                        DetailedRecipeFragmentDirections.actionDetailedRecipeFragmentToRecipeFragment();
                Navigation.findNavController(binding.getRoot()).navigate(action);
            }
        });
        return rootView;
    }

    // This method is called when the view is no longer needed; prevents memory leaks
    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}

