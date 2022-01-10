package com.project.recipebook.recipelist.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.recipebook.R
import com.project.recipebook.base.model.Recipe

class RecipesRVAdapter(
    private val recipes: List<Recipe>,
    private val onItemClicked: (Int) -> Unit
) : RecyclerView.Adapter<RecipeRVViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeRVViewHolder {
        return RecipeRVViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecipeRVViewHolder, position: Int) {
        holder.bind(recipes[position], onItemClicked)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}