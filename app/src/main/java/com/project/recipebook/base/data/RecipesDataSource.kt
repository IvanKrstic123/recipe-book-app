package com.project.recipebook.base.data

import com.project.recipebook.base.functional.Either
import com.project.recipebook.base.model.Recipe
import com.project.recipebook.base.model.RecipeDetails
import com.project.recipebook.base.model.Recipes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import java.lang.Exception

interface IRecipesDataSource {
    suspend fun getRecipeById(id: Int): Either<RecipeDetails>
    suspend fun getRecipeHighProtein(value: Int): Either<Recipes>
    suspend fun getRecipeLowFat(value: Int): Either<Recipes>
    suspend fun getRecipeLowCarbs(value: Int): Either<Recipes>
    suspend fun getRecipeHighCarbs(value: Int): Either<Recipes>
}

class RecipesDataSource(private val apiService: RecipesApiService): IRecipesDataSource {

    companion object {
/*        private const val API_KEY =
            "6801d9340c4b4bd6a20ebb42b5a3f10f"*/

/*        private const val API_KEY =
            "4019c237997d4d9289a8cbfe6689c2ee"*/

        private const val API_KEY =
            "df003d30d09c47bdb6d3aac4213958d8"
    }

    override suspend fun getRecipeById(id: Int): Either<RecipeDetails> = handleCall(apiService.getRecipeById(id, API_KEY))

    override suspend fun getRecipeHighProtein(value: Int): Either<Recipes> = handleCall(apiService.getRecipeHighProtein(API_KEY, value))

    override suspend fun getRecipeLowFat(value: Int): Either<Recipes> = handleCall(apiService.getRecipeLowFat(API_KEY, value))

    override suspend fun getRecipeLowCarbs(value: Int): Either<Recipes> = handleCall(apiService.getRecipeLowCarbs(API_KEY, value))

    override suspend fun getRecipeHighCarbs(value: Int): Either<Recipes> = handleCall(apiService.getRecipeHighCarbs(API_KEY, value))

    private suspend fun <T> handleCall(call: Call<T>): Either<T> {

        return withContext(Dispatchers.IO) {
            val response = call.execute()

            println(response)

            if (response.isSuccessful) {
                Either.Success(response.body()!!)
            } else {
                Either.Error(Exception(response.message()))
            }
        }

    }
}