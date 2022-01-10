package com.project.recipebook.base.model

import com.google.gson.annotations.SerializedName

class Recipes (
    @SerializedName("results") val list: List<Recipe>
)

