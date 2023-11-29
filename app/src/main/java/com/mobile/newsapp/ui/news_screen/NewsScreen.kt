package com.mobile.newsapp.ui.news_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mobile.newsapp.ui.component.NewsArticleCard

@Composable
fun NewsScreen(
    viewModel: NewsScreenViewModel = hiltViewModel()
){
    // LazyColumn to efficiently handle large lists of items
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        // Iterate over the list of articles provided by the ViewModel
        items(viewModel.articles) { article ->
            // Display each news article using the NewsArticleCard composable
            NewsArticleCard(article = article, onCardClicked = {})
        }
    }
}