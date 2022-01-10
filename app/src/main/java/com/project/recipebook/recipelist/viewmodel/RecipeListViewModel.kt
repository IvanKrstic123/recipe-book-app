package com.project.recipebook.recipelist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.recipebook.base.functional.Either
import com.project.recipebook.base.data.IRecipesDataSource
import com.project.recipebook.recipelist.view.RecipeListViewState
import com.project.recipebook.recipelist.view.RecipeListViewState.DataReceived
import com.project.recipebook.recipelist.view.RecipeListViewState.ErrorReceived
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeListViewModel(private val dataSource: IRecipesDataSource) : ViewModel() {

    private val _state = MutableLiveData<RecipeListViewState>()
    val state: LiveData<RecipeListViewState>
        get() = _state

    fun getRecipes(catName: String, value: Int) {
        viewModelScope.launch(Dispatchers.IO) {

            _state.postValue(RecipeListViewState.Processing)

            when(catName) {
                "HighProtein" -> _state.postValue(
                    when (val result = dataSource.getRecipeHighProtein(value)) {
                        is Either.Success -> DataReceived(result.data.list)
                        is Either.Error -> ErrorReceived(result.exception.toString())
                    }
                )
                "LowFat" -> _state.postValue(
                    when (val result = dataSource.getRecipeLowFat(value)) {
                        is Either.Success -> DataReceived(result.data.list)
                        is Either.Error -> ErrorReceived(result.exception.toString())
                    }
                )
                "LowCalorie" -> _state.postValue(
                    when (val result = dataSource.getRecipeLowCarbs(value)) {
                        is Either.Success -> DataReceived(result.data.list)
                        is Either.Error -> ErrorReceived(result.exception.toString())
                    }
                )
                "HighCalorie" -> _state.postValue(
                    when (val result = dataSource.getRecipeHighCarbs(value)) {
                        is Either.Success -> DataReceived(result.data.list)
                        is Either.Error -> ErrorReceived(result.exception.toString())
                    }
                )
            }
        }
    }
}