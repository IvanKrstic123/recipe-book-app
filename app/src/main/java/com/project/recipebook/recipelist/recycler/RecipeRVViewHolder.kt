package com.project.recipebook.recipelist.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.recipebook.base.model.Recipe
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipeRVViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(recipe: Recipe, onItemClicked: (Int) -> Unit) {
        itemView.recipeName.text = recipe.title
        itemView.recipeDescription.text = "some description"
        Glide.with(itemView).load(recipe.image).into(itemView.recipeImg)

        itemView.setOnClickListener { onItemClicked.invoke(recipe.id) }
    }
}