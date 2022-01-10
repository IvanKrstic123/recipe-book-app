package com.project.recipebook.base


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.project.recipebook.R
import com.project.recipebook.homepage.SplashFragment
import com.project.recipebook.recipecategorylist.RecipeCategoryListFragment
import com.project.recipebook.recipedetails.RecipeDetailsFragment
import com.project.recipebook.recipelist.view.RecipeListFragment

class MainActivity : AppCompatActivity(), ICoordinator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showFragment(SplashFragment(), true)
    }

    private fun showFragment(fragment: Fragment, addAsRoot: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        if (!addAsRoot) transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun showRecipeList(categoryName: String?, value: Int) {
        showFragment(RecipeListFragment.newInstance(categoryName!!, value.toString()))
    }

    override fun shopRecipeDetails(id: Int) {
        showFragment(RecipeDetailsFragment.newInstance(id))
    }

    override fun showRecipeCategoryList() {
        showFragment(RecipeCategoryListFragment())
    }
}