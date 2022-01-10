package com.project.recipebook.base.data

object ApiServiceProvider {
    val recipesApiService: RecipesApiService = RetrofitBuilder.retrofit.create(RecipesApiService::class.java)}