package com.mobileapp.recipepal;

import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.mobileapp.recipepal.databinding.FragmentRecipeBinding;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class RecipeFragment extends Fragment {
    private FragmentRecipeBinding binding;
    private RecipeViewModel recipeViewModel;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();


    Dialog dialog;
    Button buttonDialogCancel, buttonDialogDelete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRecipeBinding.inflate(inflater, container, false);

        View view = binding.getRoot();
        // Inflate the layout for this fragment

        recipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);

//        dialog = new Dialog(binding.getRoot().getContext());
//        dialog.setContentView(R.layout.dialog_box);
//        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//        dialog.getWindow().setBackgroundDrawable(getActivity().getDrawable(R.drawable.dialog_background));
//        dialog.setCancelable(false);
//
//        buttonDialogCancel = dialog.findViewById(R.id.cancelDeleteButton);
//        buttonDialogDelete = dialog.findViewById(R.id.confirmDeleteButton);
//
//        buttonDialogCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//        buttonDialogDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });


        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action
                        = RecipeFragmentDirections.actionRecipeFragmentToCreateRecipeFragment();
                Navigation.findNavController(binding.getRoot()).navigate(action);
            }
        });


        recipeViewModel.getAllRecipes().observe(getViewLifecycleOwner(), new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipeList) {
                RecyclerView recyclerView = binding.recyclerView;
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                Adapter adapter = new Adapter(recipeList, recipeViewModel);
                recyclerView.setAdapter(adapter);
            }
        });


        return view;
    }
}
