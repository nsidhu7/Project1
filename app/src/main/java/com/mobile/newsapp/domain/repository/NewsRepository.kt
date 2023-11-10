package com.mobile.newsapp.domain.repository

import com.mobile.newsapp.domain.model.Article
import com.mobile.newsapp.util.Resource

interface NewsRepository {

    suspend fun getTopHeadlines(
        category: String
    ): Resource<List<Article>>
}