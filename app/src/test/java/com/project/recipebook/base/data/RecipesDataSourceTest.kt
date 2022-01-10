package com.project.recipebook.base.data

import com.project.recipebook.base.functional.Either
import com.project.recipebook.base.model.Recipes
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.openMocks
import retrofit2.Call
import retrofit2.Response

class RecipesDataSourceTest {

    @Mock
    lateinit var getRecipesCall: Call<Recipes>

    @Mock
    lateinit var apiService: RecipesApiService

    lateinit var dataSource: IRecipesDataSource

    @Before
    fun setup() {
        openMocks(this)
        dataSource = RecipesDataSource(apiService)
    }

    @Test
    fun `test getRecipes, has response, Success returned` () = runBlocking {

        val expectedRecipes = Recipes(listOf())
        val expectedResult = Either.Success(expectedRecipes)

        `when` (apiService.getRecipeHighProtein(anyString(), anyInt())).thenReturn(getRecipesCall)
        `when` (getRecipesCall.execute()).thenReturn(Response.success(expectedRecipes))

        val result = dataSource.getRecipeHighProtein(100)

        assertEquals(expectedResult, result)
    }

    @Test
    fun `test getRecipes, has error, Error Returned` () = runBlocking {

        val expectedResponseBody = ResponseBody.create(
            MediaType.parse("application/json"), ""
        )

        `when` (apiService.getRecipeHighProtein(anyString(), anyInt())).thenReturn(getRecipesCall)
        `when` (getRecipesCall.execute()).thenReturn(Response.error(400, expectedResponseBody))

        val result = dataSource.getRecipeHighProtein(800)

        assertTrue(result is Either.Error)
    }
}