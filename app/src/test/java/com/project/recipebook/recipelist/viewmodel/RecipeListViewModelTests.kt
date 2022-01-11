package com.project.recipebook.recipelist.viewmodel

import androidx.lifecycle.Observer
import com.project.recipebook.base.InstantExecutorTest
import com.project.recipebook.base.data.IRecipesDataSource
import com.project.recipebook.base.functional.Either
import com.project.recipebook.base.model.Nutrition
import com.project.recipebook.base.model.Recipe
import com.project.recipebook.base.model.Recipes
import com.project.recipebook.recipelist.view.RecipeListViewState
import com.project.recipebook.recipelist.view.RecipeListViewState.Processing
import com.project.recipebook.recipelist.view.RecipeListViewState.DataReceived
import com.project.recipebook.recipelist.view.RecipeListViewState.ErrorReceived
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.openMocks
import java.util.*

class RecipeListViewModelTests: InstantExecutorTest() {

    @Mock
    lateinit var dataSource: IRecipesDataSource

    @Mock
    lateinit var stateObserver: Observer<RecipeListViewState>

    lateinit var viewModel: RecipeListViewModel

    @Before
    fun setup() {
        openMocks(this)
        viewModel = RecipeListViewModel(dataSource)
        viewModel.state.observeForever(stateObserver)
    }

    @Test
    fun `test getRecipes, has result, state changed to Processing - DataReceived` () = runBlocking {

        val expectedResult = Recipes(listOf(
            Recipe(23211,"Pancake", "http://someurl.com", Nutrition(listOf())
        )))

        `when` (dataSource.getRecipeHighProtein(anyInt())).thenReturn(Either.Success(expectedResult))

        viewModel.getRecipes("HighProtein", 150)

        verify(stateObserver).onChanged(Processing)
        verify(stateObserver).onChanged(DataReceived(expectedResult.list))
    }

    @Test
    fun `test getRecipes, has error, state changed to Processing - ErrorReceived` () = runBlocking {

        val expectedResult = Exception()

        `when` (dataSource.getRecipeHighProtein(anyInt())).thenReturn(Either.Error(expectedResult))

        viewModel.getRecipes("HighProtein", 20)

        verify(stateObserver).onChanged(Processing)
        verify(stateObserver).onChanged(ErrorReceived(expectedResult.toString()))
    }
}