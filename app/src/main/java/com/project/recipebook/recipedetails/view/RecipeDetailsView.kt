package com.project.recipebook.recipedetails.view

import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.project.recipebook.R
import com.project.recipebook.base.model.Recipe

class RecipeDetailsView(context: Context): ConstraintLayout(context) {

    private val view = View.inflate(context, R.layout.item_recipe_details, this)

    fun bind(recipe: Recipe) {
    }
}