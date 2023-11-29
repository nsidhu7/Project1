package com.mobile.newsapp.ui.news_screen


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.newsapp.domain.model.Article
import com.mobile.newsapp.domain.repository.NewsRepository
import com.mobile.newsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsScreenViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel()
{
    // The list of articles, observed by the UI
    var articles by mutableStateOf<List<Article>>(emptyList())

    init { // Initialization block to fetch news articles on ViewModel creation
        getNewsArticles(category = "general")
    }

    private fun getNewsArticles(category: String){     // Function to get news articles from the repository
        viewModelScope.launch {
            val result = newsRepository.getTopHeadlines(category)
            when (result) {
                is Resource.Success -> {
                    // Set the list of articles on successful data retrieval
                    articles = result.data ?: emptyList()
                }
                // Set the list of articles on successful data retrieval
                is Resource.Error -> TODO()
            }
        }
    }
}