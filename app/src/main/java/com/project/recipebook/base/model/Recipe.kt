package com.project.recipebook.base.model

import com.google.gson.annotations.SerializedName

data class Nutrient(
    val title: String,
    val name: String,
    val amount: Double,
    val unit: String
)

data class Nutrition (
    @SerializedName("nutrients") val list: List<Nutrient>
)

class Recipe(
    val id: Int,
    val title: String,
    val image: String,
    val nutrition: Nutrition
) {
    override fun toString(): String {
        return "${nutrition.list[0].title} : ${nutrition.list[0].amount} ${nutrition.list[0].unit}"
    }
}

class Ingredient(
    val aisle: String,
    val original: String
)

class RecipeDetails(
    val extendedIngredients: List<Ingredient>
)




