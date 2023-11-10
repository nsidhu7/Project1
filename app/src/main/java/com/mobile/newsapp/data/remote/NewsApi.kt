package com.mobile.newsapp.data.remote

import com.mobile.newsapp.domain.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import java.nio.channels.spi.AbstractSelectionKey

interface NewsApi {

    // GET https://newsapi.org/v2/top-headlines?country=us&apiKey=14ad125f5a5748c4b36f180c8bb611a5

    @GET("top-headlines")
    suspend fun getBreakingNews(
        @Query("category") category: String,
        @Query("country") country: String = "US",
        @Query("apikey") apiKey: String = API_KEY
    ) : NewsResponse
    companion object{
        const val BASE_URL = "https://newsapi.org/v2"
        const val API_KEY = "14ad125f5a5748c4b36f180c8bb611a5"
    }
}