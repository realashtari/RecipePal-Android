package com.mobileapp.recipepal;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mobileapp.recipepal.databinding.FragmentRecipeBinding;

import java.util.List;

public class RecipeFragment extends Fragment {
    private FragmentRecipeBinding binding;

    Dialog dialog;
    Button buttonDialogCancel, buttonDialogDelete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRecipeBinding.inflate(inflater, container, false);

        View view = binding.getRoot();
        // Inflate the layout for this fragment

        dialog = new Dialog(binding.getRoot().getContext());
        dialog.setContentView(R.layout.dialog_box);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getActivity().getDrawable(R.drawable.dialog_background));
        dialog.setCancelable(false);

        buttonDialogCancel = dialog.findViewById(R.id.cancelDeleteButton);
        buttonDialogDelete = dialog.findViewById(R.id.confirmDeleteButton);

        buttonDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        buttonDialogDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


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

//        binding.viewRecipeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//                NavDirections action
//                        = RecipeFragmentDirections.actionRecipeFragmentToDetailedRecipeFragment();
//                Navigation.findNavController(binding.getRoot()).navigate(action);
//            }
//        });

        binding.viewRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Fetch recipes in a background thread to avoid blocking the UI
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase database = AppDatabase.getDatabase(requireContext());

                        // Fetch all recipes
                        List<Recipe> recipes = database.recipeDao().getAll();

                        if (!recipes.isEmpty()) {
                            // Dynamically get the first recipe ID (or select another logic for choosing a recipe)
                            int insertedId = recipes.get(1).uid; // Replace with logic for selecting a recipe ID

                            // Log the ID for debugging purposes
                            Log.d("RecipeFragment", "Using insertedId: " + insertedId);

                            // Navigate to DetailedRecipeFragment with the dynamically fetched recipe ID
                            requireActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // Pass the recipe ID to DetailedRecipeFragment
                                    NavDirections action = RecipeFragmentDirections
                                            .actionRecipeFragmentToDetailedRecipeFragment(insertedId);
                                    Navigation.findNavController(binding.getRoot()).navigate(action);
                                }
                            });
                        } else {
                            Log.e("RecipeFragment", "No recipes found in the database!");
                        }
                    }
                }).start();
            }
        });


        binding.deleteRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });


        return view;
    }
}
