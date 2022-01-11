package com.project.recipebook.recipedetails.viewmodel

import com.project.recipebook.base.model.RecipeDetails

sealed class RecipeDetailsViewState {

    object Processing: RecipeDetailsViewState()
    data class DataReceived(val recipeDetails: RecipeDetails): RecipeDetailsViewState()
    data class ErrorReceived(val message: String): RecipeDetailsViewState()
}