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


// ViewModel class responsible for managing UI-related data and events for the NewsScreen
@HiltViewModel
class NewsScreenViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {
    // The list of articles, observed by the UI
    var articles by mutableStateOf<List<Article>>(emptyList())

    // ViewModel state to manage UI-related data and events
    var state by mutableStateOf(NewsScreenState())

    // Function to handle various events triggered by the UI
    fun onEvent(event: NewsScreenEvent) {
        when(event) {
            // Event: Category changed by the user
            is NewsScreenEvent.OnCategoryChanged -> {
                // Update the category in the ViewModel state and fetch new articles
                state = state.copy(category =  event.category)
                getNewsArticles(state.category)
            }
            // Event: User clicked on a news card
            is NewsScreenEvent.OnNewsCardClicked -> {
                state = state.copy(selectedArticle = event.article)
            }
            // Event: User clicked on the search icon
            NewsScreenEvent.OnSearchIconClicked -> {
                state = state.copy(
                    isSearchBarVisible = true,
                    articles = emptyList()
                )
            }
            // Event: User clicked on the close icon in the search bar
            NewsScreenEvent.OnCloseIconClicked -> {
                state = state.copy(isSearchBarVisible = false)
            }
            // Event: TODO - User changed the search query
            is NewsScreenEvent.OnSearchQueryChanged -> TODO()
        }
    }

    // Initialization block to fetch news articles on ViewModel creation
    init {
        getNewsArticles(category = "general")
    }

    // Coroutine function to fetch news articles based on the provided category
    private fun getNewsArticles(category: String) {
        viewModelScope.launch {
            // Update loading state to indicate data retrieval is in progress
            state = state.copy(isloading = true)
            // Fetch news articles from the repository
            val result = newsRepository.getTopHeadlines(category)
            when (result) {
                // Handle successful data retrieval
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
