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
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(viewModel.articles) { article ->
            NewsArticleCard(article = article, onCardClicked = {})
        }
    }
}