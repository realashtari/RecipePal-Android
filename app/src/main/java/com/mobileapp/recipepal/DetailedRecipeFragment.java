package com.mobileapp.recipepal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.mobileapp.recipepal.databinding.FragmentDetailedRecipeBinding;

public class DetailedRecipeFragment extends Fragment {

    private FragmentDetailedRecipeBinding binding;
    //private RecipeDatabase recipeDatabase;

    // Views
    private TextView titleTextView;
    private ImageView recipeImageView;
    private TextView instructionsTextView;
    private TextView ingredientsTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetailedRecipeBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        // Initialize views using ViewBinding


        // Example: Retrieve recipe details from database
        loadRecipeFromDatabase();

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

    private void loadRecipeFromDatabase() {

    }
}
