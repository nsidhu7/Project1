package com.mobile.newsapp.data.repository

import com.mobile.newsapp.data.remote.NewsApi
import com.mobile.newsapp.domain.model.Article
import com.mobile.newsapp.domain.repository.NewsRepository
import com.mobile.newsapp.util.Resource

class NewsRepositoryImplementation(
    private val newsApi: NewsApi
): NewsRepository {
    // Implementation of the getTopHeadlines function from the NewsRepository interface
    override suspend fun getTopHeadlines(category: String): Resource<List<Article>> {
        return try {
            // Make a network request using the newsApi to get breaking news for the specified category
            val response = newsApi.getBreakingNews(category = category)
            // Return a Resource.Success with the list of articles if the request is successful
            Resource.Success(response.articles)
        }catch (e:Exception){
            // Return a Resource.Error if an exception occurs during the network request
            Resource.Error(message = "Failed to fetch news ${e.message}")
        }
    }
}