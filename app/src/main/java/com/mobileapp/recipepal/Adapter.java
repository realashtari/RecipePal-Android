package com.mobileapp.recipepal;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//Adapter Class for Recycler View
public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    // Define variables for class
    List<Recipe> items;
    RecipeViewModel recipeViewModel;
    public Context parentContext;

    //Constructor with list of items, and the viewModel
    public Adapter (List<Recipe> items, RecipeViewModel viewModel){
        this.items = items;
        this.recipeViewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        parentContext = parent.getContext();
        View view;
        view = LayoutInflater.from(parentContext).
                inflate(R.layout.card_template, parent, false);
        return new ViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String imageUrl = items.get(position).ImageUrl;
        holder.recipeLabelTextView.setText(items.get(position).recipeName);

        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(parentContext)
                    .load(imageUrl)
                    .override(600,400)// Load the image from the URL
                    .into(holder.foodImageView); // ImageView where you want to display the image
        } else {
            // Case where there is no image URL, default image used
            Glide.with(parentContext)
                    .load(R.drawable.default_food)
                    .override(600,400)
                    .into(holder.foodImageView);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

//View Holder for RecyclerView
class ViewHolder extends RecyclerView.ViewHolder {
    // Declare UI components
    TextView recipeLabelTextView;
    ImageView foodImageView;
    private Adapter adapter;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    //constructor
    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        // Initialize UI components
        recipeLabelTextView = itemView.findViewById(R.id.recipeLabelTextView);
        foodImageView = itemView.findViewById(R.id.foodImageView);

        //Listener for Delete Button
        itemView.findViewById(R.id.deleteRecipeButton).setOnClickListener(view ->  {
            Recipe recipe = adapter.items.get(getAdapterPosition());

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    adapter.recipeViewModel.deleteSingle(recipe);
                }
            });

            adapter.items.remove(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());
        });

        itemView.findViewById(R.id.viewRecipeButton).setOnClickListener(view -> {
            NavDirections action = RecipeFragmentDirections.
                    actionRecipeFragmentToDetailedRecipeFragment(adapter.items.get(getAdapterPosition()).uid);

            Navigation.findNavController((View) itemView.getParent()).navigate(action);
        });

        itemView.findViewById(R.id.editRecipeButton).setOnClickListener(view -> {
            NavDirections action =
                    RecipeFragmentDirections.actionRecipeFragmentToUpdateRecipeFragment(adapter.items.get(getAdapterPosition()).uid);

            Navigation.findNavController((View) itemView.getParent()).navigate(action);
        });
    }

    public ViewHolder linkAdapter (Adapter adapter) {
        this.adapter = adapter;
        return this;
    }
}
