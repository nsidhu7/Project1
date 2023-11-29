package com.mobile.newsapp.ui.news_screen

import com.mobile.newsapp.domain.model.Article

// Sealed class representing events that can occur on the NewsScreen
sealed class NewsScreenEvent{

    // Event triggered when a news card is clicked
    data class onNewsCardClicked(val article: Article): NewsScreenEvent()

    // Event triggered when the category is changed
    data class onCategoryChanged(val category: String): NewsScreenEvent()

    // Event triggered when the search query is changed
    data class onSearchQueryChanged(val searchQuery: String): NewsScreenEvent()

    // Event triggered when the search icon is clicked
    object onSerchIconClicked: NewsScreenEvent()

    // Event triggered when the close icon is clicked
    object onCloseIconClicked: NewsScreenEvent()
}
