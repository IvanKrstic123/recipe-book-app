package com.project.recipebook.recipecategorylist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.project.recipebook.R
import com.project.recipebook.base.model.Category

class RecipeCategoryListAdapter (
    private val categories: ArrayList<Category>,
    private val context: Context
) : BaseAdapter() {

/*    private var layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater*/

    override fun getCount(): Int = categories.size

    override fun getItem(index: Int) = categories[index]

    override fun getItemId(index: Int) = index.toLong()

    override fun getView(index: Int, view: View?, viewGroup: ViewGroup?): View {
        var viewItem = view

        if(viewItem == null) {
            viewItem = LayoutInflater.from(context).
            inflate(R.layout.item_rv_main_category, viewGroup, false);
            /*viewItem = this.layoutInflater.inflate(R.layout.item_rv_main_category, viewGroup, false)*/
        }

        val categoryImage = viewItem?.findViewById<ImageView>(R.id.img_dish)


        categoryImage?.setImageResource(categories[index].image)

        return viewItem!!
    }
}