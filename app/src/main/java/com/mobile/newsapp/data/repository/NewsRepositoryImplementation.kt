package com.mobile.newsapp.data.repository

import com.mobile.newsapp.data.remote.NewsApi
import com.mobile.newsapp.domain.model.Article
import com.mobile.newsapp.domain.repository.NewsRepository
import com.mobile.newsapp.util.Resource

class NewsRepositoryImplementation(
    private val newsApi: NewsApi
): NewsRepository {
    override suspend fun getTopHeadlines(category: String): Resource<List<Article>> {
        return try {
            val response = newsApi.getBreakingNews(category = category)
            Resource.Success(response.articles)
        }catch (e:Exception){
            Resource.Error(message = "Failed to fetch news ${e.message}")
        }
    }
}