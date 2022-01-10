package com.project.recipebook.base.data

import com.project.recipebook.base.model.Recipe
import com.project.recipebook.base.model.Recipes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY = "6801d9340c4b4bd6a20ebb42b5a3f10f"

interface RecipesApiService {


    @GET("recipes/{id}/information{apiKey}")
    fun getRecipeById(@Path("id") id: Int, @Path("apiKey") apiKey: String): Call<Recipe>

    @GET("recipes/complexSearch")
    fun getRecipeHighProtein(@Query("apiKey") apiKey: String, @Query("minProtein") value: Int): Call<Recipes>

    @GET("recipes/complexSearch")
    fun getRecipeLowFat(@Query("apiKey") apiKey: String, @Query("maxFat") value: Int): Call<Recipes>

    @GET("recipes/complexSearch")
    fun getRecipeLowCarbs(@Query("apiKey") apiKey: String, @Query("maxCalories") value: Int): Call<Recipes>

    @GET("recipes/complexSearch")
    fun getRecipeHighCarbs(@Query("apiKey") apiKey: String, @Query("minCalories") value: Int): Call<Recipes>
}