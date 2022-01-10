package com.project.recipebook.homepage

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.recipebook.R
import com.project.recipebook.base.ICoordinator
import kotlinx.android.synthetic.main.fragment_splash.*

class SplashFragment : Fragment(){

    private lateinit var contextActivity : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        contextActivity = container?.context!!
        return inflater.inflate(R.layout.fragment_splash, container, false)

        val view: View = inflater!!.inflate(R.layout.fragment_splash, container, false)

        btnGettingStarted.setOnClickListener { view ->
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showCategoryList(contextActivity)
    }

    private fun showCategoryList(contextActivity: Context) {
        btnGettingStarted.setOnClickListener { view ->
            Log.d("Nesto: ", "Button clicked")
            (activity as ICoordinator).showRecipeCategoryList()
        }
    }


}