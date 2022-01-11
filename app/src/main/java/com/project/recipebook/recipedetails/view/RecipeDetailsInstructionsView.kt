package com.project.recipebook.recipedetails.view

import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.project.recipebook.R
import com.project.recipebook.base.model.Ingredient
import kotlinx.android.synthetic.main.item_recipedetails_ingredient.view.*

class RecipeDetailsInstructionsView(context: Context): ConstraintLayout(context) {

    private val view = View.inflate(context, R.layout.item_recipedetails_ingredient, this)

    fun bind(ingredient: Ingredient) {
        view.ingredientTxt.text = ingredient.original
        print(ingredient)
//        Glide.with(itemView).load(recipe.image).into(itemView.recipeImg)
    }
}