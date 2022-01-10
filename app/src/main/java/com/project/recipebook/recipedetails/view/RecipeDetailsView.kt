package com.project.recipebook.recipedetails.view

import android.content.Context
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.project.recipebook.R
import com.project.recipebook.base.model.Recipe
import kotlinx.android.synthetic.main.fragment_recipe_details.view.*

class RecipeDetailsView(context: Context): ConstraintLayout(context) {

    private val view = View.inflate(context, R.layout.fragment_recipe_details, this)

    fun bind(recipe: Recipe) {
        view.detailsDishName.text = recipe.title
        print(recipe)
//        Glide.with(itemView).load(recipe.image).into(itemView.recipeImg)
    }
}