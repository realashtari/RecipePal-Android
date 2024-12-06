package com.mobileapp.recipepal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobileapp.recipepal.databinding.FragmentUpdateRecipeBinding;

public class UpdateRecipeFragment extends Fragment {
    private FragmentUpdateRecipeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpdateRecipeBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        binding.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
}