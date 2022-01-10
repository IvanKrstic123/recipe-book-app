package com.project.recipebook.recipelist.view

import com.project.recipebook.base.model.Recipe

sealed class RecipeListViewState {

    object Processing: RecipeListViewState()
    data class DataReceived(val recipes: List<Recipe>) : RecipeListViewState()
    data class ErrorReceived(val message: String) : RecipeListViewState()
}