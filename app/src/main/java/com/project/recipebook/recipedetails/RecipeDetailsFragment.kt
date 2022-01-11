package com.project.recipebook.recipedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.project.recipebook.R
import com.project.recipebook.base.data.ApiServiceProvider
import com.project.recipebook.base.data.RecipesDataSource
import com.project.recipebook.base.functional.ViewModelFactoryUtil
import com.project.recipebook.base.model.Recipe
import com.project.recipebook.base.model.RecipeDetails
import com.project.recipebook.recipedetails.view.RecipeDetailsInstructionsView
import com.project.recipebook.recipedetails.view.RecipeDetailsView
import com.project.recipebook.recipedetails.viewmodel.RecipeDetailsViewModel
import com.project.recipebook.recipedetails.viewmodel.RecipeDetailsViewState
import kotlinx.android.synthetic.main.fragment_recipe_details.*
import kotlinx.android.synthetic.main.fragment_recipe_list.*


class RecipeDetailsFragment : Fragment() {

    lateinit var viewModel: RecipeDetailsViewModel

    private var recipeId: Int? = null

    companion object {

        private const val CATEGORY_KEY = "CATEGORY_KEY"

        fun newInstance(recipeId: Int): RecipeDetailsFragment {
            return RecipeDetailsFragment().apply {
                arguments = Bundle().apply { putInt(CATEGORY_KEY, recipeId!!) }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recipeId = arguments?.getInt(CATEGORY_KEY)
        //initialization of view model
        viewModel = ViewModelProvider(this, ViewModelFactoryUtil.viewModelFactory {
            RecipeDetailsViewModel(RecipesDataSource(ApiServiceProvider.recipesApiService))
        }).get(RecipeDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindFromViewModel()
        viewModel.getRecipeById(recipeId!!)
    }

    private fun bindFromViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->

//            // show progress bar for processing state
//            recipeListProgressBar.isVisible = state is RecipeDetailsViewState.Processing

            when (state) {
                is RecipeDetailsViewState.DataReceived -> { setupItemView(state.recipeDetails) }
                is RecipeDetailsViewState.ErrorReceived -> state.message
            }
        }
    }

    private fun setupItemView(recipe: RecipeDetails) {

        detailsDishName.text = "${recipe.title} - ${recipe.readyInMinutes} min"
//        detailsCardImage
        Glide.with(detailsCardImage).load(recipe.image).into(detailsCardImage)

        // ingredients binding
        recipe.extendedIngredients.forEach { ingredient ->
            val view = RecipeDetailsView(requireContext())
            view.bind(ingredient)
            detailsIngredientsLayout.addView(view)
        }

        //instructions binding
        recipe.extendedIngredients.forEach { ingredient ->
            val view = RecipeDetailsInstructionsView(requireContext())
            view.bind(ingredient)
            detailsInstructionsSection.addView(view)
        }
    }
}