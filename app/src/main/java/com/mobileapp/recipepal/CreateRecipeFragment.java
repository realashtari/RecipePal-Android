package com.mobileapp.recipepal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobileapp.recipepal.databinding.FragmentCreateRecipeBinding;

public class CreateRecipeFragment extends Fragment {

    private FragmentCreateRecipeBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateRecipeBinding.inflate(inflater, container, false);

        View view = binding.getRoot();
        // Inflate the layout for this fragment

        binding.createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action
                        = CreateRecipeFragmentDirections.actionCreateRecipeFragmentToRecipeFragment();
                Navigation.findNavController(binding.getRoot()).navigate(action);
            }
        });
        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action
                        = CreateRecipeFragmentDirections.actionCreateRecipeFragmentToRecipeFragment();
                Navigation.findNavController(binding.getRoot()).navigate(action);
            }
        });
        return view;
    }
}