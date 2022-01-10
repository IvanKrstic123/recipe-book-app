package com.project.recipebook.recipedetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.recipebook.base.functional.Either
import com.project.recipebook.base.data.IRecipesDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeDetailsViewModel(private val dataSource: IRecipesDataSource) : ViewModel() {

    private val _state = MutableLiveData<RecipeDetailsViewState>()
    val state: LiveData<RecipeDetailsViewState>
        get() = _state

    fun getRecipeById(value: Int) {
        viewModelScope.launch(Dispatchers.IO) {

            _state.postValue(RecipeDetailsViewState.Processing)

            _state.postValue(
                when (val result = dataSource.getRecipeById(value)) {
                    is Either.Success -> RecipeDetailsViewState.DataReceived(result.data)
                    is Either.Error -> RecipeDetailsViewState.ErrorReceived(result.exception.toString())
                }
            )
        }
    }
}