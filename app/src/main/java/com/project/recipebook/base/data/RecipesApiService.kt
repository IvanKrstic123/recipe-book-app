package com.project.recipebook.base.data

import com.project.recipebook.base.model.Recipe
import com.project.recipebook.base.model.RecipeDetails
import com.project.recipebook.base.model.Recipes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipesApiService {

    @GET("recipes/{id}/information{apiKey}")
    fun getRecipeById(@Path("apiKey") apiKey: String, @Path("id") id: Int): Call<Recipe>

    @GET("recipes/complexSearch")
    fun getRecipeHighProtein(@Query("apiKey") apiKey: String, @Query("minProtein") value: Int): Call<Recipes>

    @GET("recipes/complexSearch")
    fun getRecipeLowFat(@Query("apiKey") apiKey: String, @Query("maxFat") value: Int): Call<Recipes>

    @GET("recipes/complexSearch")
    fun getRecipeLowCarbs(@Query("apiKey") apiKey: String, @Query("maxCalories") value: Int): Call<Recipes>

    @GET("recipes/complexSearch")
    fun getRecipeHighCarbs(@Query("apiKey") apiKey: String, @Query("minCalories") value: Int): Call<Recipes>
}