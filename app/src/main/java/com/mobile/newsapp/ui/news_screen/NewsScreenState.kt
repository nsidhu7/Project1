package com.mobile.newsapp.ui.news_screen

import com.mobile.newsapp.domain.model.Article


// Data class representing the state of the NewsScreen
data class NewsScreenState(
    // Flag indicating whether data is currently being loaded
    val isloading: Boolean = false,
    // List of articles to be displayed on the screen
    val articles: List<Article> = emptyList(),
    // Error message in case of data retrieval failure
    val error: String? = null,
    // Flag indicating whether the search bar is currently visible
    val isSearchBarVisible: Boolean = false,
    // The selected article, if any, for further details or actions
    val selectedArticle: Article? = null,
    // The current category of news being displayed
    val category: String = "General",
    // The current search query entered by the user
    val searchQuery: String = ""
)