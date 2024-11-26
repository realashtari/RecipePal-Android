package com.mobileapp.recipepal;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mobileapp.recipepal.databinding.FragmentRecipeBinding;

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

        binding.viewRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action
                        = RecipeFragmentDirections.actionRecipeFragmentToDetailedRecipeFragment();
                Navigation.findNavController(binding.getRoot()).navigate(action);
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