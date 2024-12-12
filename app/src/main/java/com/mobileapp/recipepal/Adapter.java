package com.mobileapp.recipepal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.mobileapp.recipepal.databinding.CardTemplateBinding;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* Adapter Class for RecyclerView
* Helper class to dynamically load and populate the cards with data from the Room database.
*/
public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    // Define variables for the adapter
    List<Recipe> items;
    RecipeViewModel recipeViewModel;
    public Context parentContext;

    // Constructor to initialize the adapter
    public Adapter(List<Recipe> items, RecipeViewModel viewModel) {
        this.items = items;
        this.recipeViewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        parentContext = parent.getContext();
        View view = LayoutInflater.from(parentContext)
                .inflate(R.layout.card_template, parent, false);
        return new ViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = items.get(position); // Get the recipe for this position
        holder.bindData(recipe); // Use the bindData method to bind the data to views

        String imageUrl = recipe.ImageUrl;
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(parentContext)
                    .load(imageUrl)
                    .override(600, 400) // Load the image from the URL
                    .into(holder.binding.foodImageView); // ImageView where you want to display the image
        } else {
            // Case where there is no image URL, use the default image
            Glide.with(parentContext)
                    .load(R.drawable.default_food)
                    .override(600, 400)
                    .into(holder.binding.foodImageView);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

// ViewHolder class for RecyclerView
class ViewHolder extends RecyclerView.ViewHolder {
    private Adapter adapter;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    public final CardTemplateBinding binding; // ViewBinding for the layout

    // Constructor
    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        // Use ViewBinding to bind the views
        binding = CardTemplateBinding.bind(itemView);

        // Set up click listeners using the binding views directly
        binding.deleteRecipeButton.setOnClickListener(view -> {
            Recipe recipe = adapter.items.get(getAdapterPosition());

            executorService.execute(() -> adapter.recipeViewModel.deleteSingle(recipe));

            adapter.items.remove(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());
        });

        binding.viewRecipeButton.setOnClickListener(view -> {
            NavDirections action = RecipeFragmentDirections
                    .actionRecipeFragmentToDetailedRecipeFragment(adapter.items.get(getAdapterPosition()).uid);

            Navigation.findNavController(itemView).navigate(action);
        });

        binding.editRecipeButton.setOnClickListener(view -> {
            NavDirections action = RecipeFragmentDirections
                    .actionRecipeFragmentToUpdateRecipeFragment(adapter.items.get(getAdapterPosition()).uid);

            Navigation.findNavController(itemView).navigate(action);
        });
    }

    // Method to bind data from a Recipe object to the ViewHolder's views
    public void bindData(Recipe recipe) {
        binding.recipeLabelTextView.setText(recipe.recipeName);
    }

    // Link the adapter to the ViewHolder
    public ViewHolder linkAdapter(Adapter adapter) {
        this.adapter = adapter;
        return this;
    }
}
