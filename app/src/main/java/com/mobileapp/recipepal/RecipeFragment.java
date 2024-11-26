package com.mobileapp.recipepal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobileapp.recipepal.databinding.FragmentRecipeBinding;

public class RecipeFragment extends Fragment {
    private FragmentRecipeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRecipeBinding.inflate(inflater, container, false);

        View view = binding.getRoot();
        // Inflate the layout for this fragment

        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action
                        = RecipeFragmentDirections.actionRecipeFragmentToCreateRecipeFragment();
                Navigation.findNavController(binding.getRoot()).navigate(action);
            }
        });

        binding.editRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action
                        = RecipeFragmentDirections.actionRecipeFragmentToUpdateRecipeFragment();
                Navigation.findNavController(binding.getRoot()).navigate(action);
            }
        });

        binding.viewRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action
                        = RecipeFragmentDirections.actionRecipeFragmentToDetailedRecipeFragment();
                Navigation.findNavController(binding.getRoot()).navigate(action);
            }
        });


        return view;
    }
}