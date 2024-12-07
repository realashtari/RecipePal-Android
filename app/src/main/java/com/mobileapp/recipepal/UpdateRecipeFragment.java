package com.mobileapp.recipepal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobileapp.recipepal.databinding.FragmentUpdateRecipeBinding;

public class UpdateRecipeFragment extends Fragment {
    private FragmentUpdateRecipeBinding binding;
    private UpdateRecipeViewModel viewModel;
    private UpdateRecipeViewModelFactory viewModelFactory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpdateRecipeBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        // Get key for the recipe
        if (getArguments() == null) {
            throw new IllegalArgumentException("SafeArgs in UpdateRecipeFragment are null");
        }

        int recipeId = UpdateRecipeFragmentArgs.fromBundle(getArguments()).getRecipeId();

        //Initialize the ViewModelFactory
        viewModelFactory = new UpdateRecipeViewModelFactory(requireActivity().getApplication(), recipeId);
        // Initialize the ViewModel
        viewModel = new ViewModelProvider(this, viewModelFactory).get(UpdateRecipeViewModel.class);

        // Observe LiveData from ViewModel for the recipe details
        viewModel.getRecipeById().observe(getViewLifecycleOwner(), new Observer<Recipe>() {
            @Override
            public void onChanged(Recipe recipe) {
                if (recipe != null) {
                    // Set the values to the UI
                    binding.titleText.setText(recipe.recipeName);
                    binding.ingredientsText.setText(recipe.Ingredients);
                    binding.instructionsText.setText(recipe.Instructions);
                    binding.urlText.setText(recipe.ImageUrl);
                }
            }
        });

        binding.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.update(
                        binding.titleText.getText().toString(),
                        binding.instructionsText.getText().toString(),
                        binding.ingredientsText.getText().toString(),
                        binding.urlText.getText().toString());

                NavDirections action
                        = UpdateRecipeFragmentDirections.actionUpdateRecipeFragmentToRecipeFragment();
                Navigation.findNavController(binding.getRoot()).navigate(action);
            }
        });

        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action
                        = UpdateRecipeFragmentDirections.actionUpdateRecipeFragmentToRecipeFragment();
                Navigation.findNavController(binding.getRoot()).navigate(action);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
