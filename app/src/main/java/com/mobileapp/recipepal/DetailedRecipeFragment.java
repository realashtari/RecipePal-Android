package com.mobileapp.recipepal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobileapp.recipepal.databinding.FragmentDetailedRecipeBinding;

public class DetailedRecipeFragment extends Fragment {
    private FragmentDetailedRecipeBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailedRecipeBinding.inflate(inflater, container, false);

        View view = binding.getRoot();
        // Inflate the layout for this fragment

        binding.closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action =
                        DetailedRecipeFragmentDirections.actionDetailedRecipeFragmentToRecipeFragment();
                Navigation.findNavController(binding.getRoot()).navigate(action);
            }
        });
        return view;
    }
}