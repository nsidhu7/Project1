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

    // ViewModel state to manage UI-related data and events
    var state by mutableStateOf(NewsScreenState())

    fun onEvent(event: NewsScreenEvent){  // Function to handle all events
        when(event){
            is NewsScreenEvent.OnCategoryChanged -> {
                // Update the category in the ViewModel state and fetch new articles
                state = state.copy(category =  event.category)
                getNewsArticles(state.category)
            }
            is NewsScreenEvent.OnNewsCardClicked -> {
                state = state.copy(selectedArticle = event.article)
            }

            NewsScreenEvent.OnSearchIconClicked -> {
                state = state.copy(
                    isSearchBarVisible = true,
                    articles = emptyList())
            }
            NewsScreenEvent.OnCloseIconClicked -> {
                state = state.copy(isSearchBarVisible = false)
            }
            is NewsScreenEvent.OnSearchQueryChanged -> TODO()

        }
    }

    init { // Initialization block to fetch news articles on ViewModel creation
        getNewsArticles(category = "general")
    }

    private fun getNewsArticles(category: String){  // Coroutine scope to perform asynchronous operations
        viewModelScope.launch {
            // Update loading state to indicate data retrieval is in progress
            state = state.copy(isloading = true)
            // Fetch news articles from the repository
            val result = newsRepository.getTopHeadlines(category)
            when (result) {
                is Resource.Success -> {
                    // Set the list of articles on successful data retrieval
                    state = state.copy(
                        articles = result.data ?: emptyList(),
                        isloading = false,
                        error = null
                    )
                }
                // Handle error scenario
                is Resource.Error -> {
                    state = state.copy(
                        error = result.message,
                        isloading = false,
                        articles = emptyList()
                    )
                }
            }
        }
    }
}