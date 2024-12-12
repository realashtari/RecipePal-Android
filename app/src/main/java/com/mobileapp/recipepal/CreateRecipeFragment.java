package com.mobileapp.recipepal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mobileapp.recipepal.databinding.FragmentCreateRecipeBinding;

/**
 * Fragment Responsible for adding new recipes to the database
 */
public class CreateRecipeFragment extends Fragment {

    private FragmentCreateRecipeBinding binding;
    private CreateRecipeViewModel createRecipeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateRecipeBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        // Initialize the ViewModel
        createRecipeViewModel = new ViewModelProvider(this).get(CreateRecipeViewModel.class);

        binding.createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleText = binding.titleText.getText().toString();
                String instructionsText = binding.instructionsText.getText().toString();
                String ingredientsText = binding.ingredientsText.getText().toString();
                String imageURL = binding.urlText.getText().toString();

                if (titleText.isEmpty() || instructionsText.isEmpty() || ingredientsText.isEmpty()) {
                    Toast.makeText(getActivity().getApplicationContext(),"Cannot submit with empty fields!", Toast.LENGTH_SHORT).show();
                    return;
                }

                createRecipeViewModel.addRecipe(titleText, ingredientsText, instructionsText, imageURL);
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

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
