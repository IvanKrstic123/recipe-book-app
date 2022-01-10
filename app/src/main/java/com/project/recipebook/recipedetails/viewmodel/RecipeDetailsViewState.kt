package com.project.recipebook.recipedetails.viewmodel

import com.project.recipebook.base.model.Recipe

sealed class RecipeDetailsViewState {

    object Processing: RecipeDetailsViewState()
    data class DataReceived(val recipes: RecipeDet): RecipeDetailsViewState()
    data class ErrorReceived(val message: String): RecipeDetailsViewState()
}