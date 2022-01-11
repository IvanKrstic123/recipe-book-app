package com.project.recipebook.recipelist.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.project.recipebook.R
import com.project.recipebook.base.ICoordinator
import com.project.recipebook.base.data.ApiServiceProvider
import com.project.recipebook.base.data.RecipesDataSource
import com.project.recipebook.base.functional.ViewModelFactoryUtil
import com.project.recipebook.base.model.Recipe
import com.project.recipebook.recipelist.recycler.RecipesRVAdapter
import com.project.recipebook.recipelist.view.RecipeListViewState
import com.project.recipebook.recipelist.viewmodel.RecipeListViewModel
import kotlinx.android.synthetic.main.fragment_recipe_list.*


class RecipeListFragment : Fragment() {
    lateinit var viewModel: RecipeListViewModel

    private var categoryName: String? = null
    private var value: Int? = null

    companion object {

        private const val CATEGORY_KEY = "CATEGORY_KEY"

        fun newInstance(categoryName: String, value: String): RecipeListFragment {
            return RecipeListFragment().apply {
                arguments = Bundle().apply { putStringArrayList(CATEGORY_KEY, arrayListOf(categoryName, value)) }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val arrayOfArguments: ArrayList<String>? = arguments?.getStringArrayList(CATEGORY_KEY)
        categoryName = arrayOfArguments?.get(0).toString()
        value = arrayOfArguments?.get(1)?.toInt()
        //initialization of view model
        viewModel = ViewModelProvider(this, ViewModelFactoryUtil.viewModelFactory {
            RecipeListViewModel(RecipesDataSource(ApiServiceProvider.recipesApiService))
        }).get(RecipeListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindFromViewModel()
        viewModel.getRecipes(categoryName!!, value!!)
    }

    private fun bindFromViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->

            // show progress bar for processing state
            recipeListProgressBar.isVisible = state is RecipeListViewState.Processing

            when (state) {
                is RecipeListViewState.DataReceived -> { setupRecyclerView(state.recipes) }
                is RecipeListViewState.ErrorReceived -> state.message
            }
        }
    }

    private fun setupRecyclerView(recipes: List<Recipe>) {
        recipeListRV.adapter = RecipesRVAdapter(recipes) { recipeId -> // pass function to adapter
            (activity as ICoordinator).shopRecipeDetails(recipeId)
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}