package com.example.finalappsaumit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private ArrayList<RecipeInfo> recipeInfoArrayList;
    private Context parentContext;

    public RecipeAdapter(ArrayList<RecipeInfo> recipeInfoArrayList, Context parentContext) {
        this.recipeInfoArrayList = recipeInfoArrayList;
        this.parentContext = parentContext;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapterxml, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        RecipeInfo recipeInfo = recipeInfoArrayList.get(position);
        holder.title.setText(recipeInfo.getTitle());
        holder.servings.setText("URL: " + recipeInfo.getServings());
        holder.readytime.setText("Time to get Ready: " + recipeInfo.getReadyMins() + " mins");
        holder.summary.setText("Health Score: " + recipeInfo.getSummary());
        Picasso.get().load(recipeInfo.getImage()).into(holder.recipePicture);

    }

    @Override
    public int getItemCount() {
        return recipeInfoArrayList.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView title, servings, readytime, summary;
        ImageView recipePicture;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.recyclerRecipeTitle);
            servings = itemView.findViewById(R.id.recyclerRecipeServings);
            readytime = itemView.findViewById(R.id.recyclerRecipeReadyTime);
            recipePicture = itemView.findViewById(R.id.recyclerRecipeImage);
            summary = itemView.findViewById(R.id.recyclerSummary);

        }
    }
}
