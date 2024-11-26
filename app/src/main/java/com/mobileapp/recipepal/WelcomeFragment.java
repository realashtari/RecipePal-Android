package com.mobileapp.recipepal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobileapp.recipepal.databinding.FragmentWelcomeBinding;

public class WelcomeFragment extends Fragment {
    private FragmentWelcomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false);

        View view = binding.getRoot();
        // Inflate the layout for this fragment

        binding.welcomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action
                        = WelcomeFragmentDirections.actionWelcomeFragmentToRecipeFragment();
                Navigation.findNavController(binding.getRoot()).navigate(action);
            }
        });
        return view;
    }
}