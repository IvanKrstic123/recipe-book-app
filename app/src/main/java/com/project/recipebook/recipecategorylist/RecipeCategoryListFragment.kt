package com.project.recipebook.recipecategorylist

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.recipebook.R
import com.project.recipebook.base.ICoordinator
import com.project.recipebook.base.model.Category
import com.project.recipebook.recipecategorylist.adapter.RecipeCategoryListAdapter
import kotlinx.android.synthetic.main.fragment_recipe_category_list.*

class RecipeCategoryListFragment : Fragment() {

    private val categoryMap = hashMapOf<String, Int>(
        "HighProtein" to R.drawable.cat_img,
        "LowFat" to R.drawable.cat_img,
        "LowCalorie" to R.drawable.cat_img,
        "HighCalorie" to R.drawable.cat_img
    )

    private val categoryList:ArrayList<Category>  = arrayListOf(
        Category("HighProtein", 80, categoryMap.getValue("HighProtein").toInt()),
        Category("LowFat", 10 ,categoryMap.getValue("LowFat").toInt()),
        Category("LowCalorie", 10, categoryMap.getValue("LowCalorie").toInt()),
        Category("HighCalorie", 10, categoryMap.getValue("HighCalorie").toInt()),
    )

    private lateinit var contextActivity : Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        contextActivity = container?.context!!
        return inflater.inflate(R.layout.fragment_recipe_category_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showRecipeList(contextActivity)
    }

    private fun showRecipeList(context: Context) {

        val categoryAdapter = RecipeCategoryListAdapter(categoryList, context)
        categoryGridView.adapter = categoryAdapter

        categoryGridView.setOnItemClickListener { adapterView, view, i, l ->
            Log.d("Nesto: ", categoryList[i].value.toString())
            (activity as ICoordinator).showRecipeList(categoryList[i].name,categoryList[i].value)
        }
    }
}