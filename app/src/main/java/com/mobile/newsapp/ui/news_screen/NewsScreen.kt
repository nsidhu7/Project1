package com.mobile.newsapp.ui.news_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.mobile.newsapp.ui.component.CategoryTabRow
import com.mobile.newsapp.ui.component.NewsArticleCard
import com.mobile.newsapp.ui.component.NewsScreenTopBar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalPagerApi::class
)
@Composable
fun NewsScreen(
    state: NewsScreenState,
    onEvent: (NewsScreenEvent) -> Unit
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val pagerState = com.google.accompanist.pager.rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val categories = listOf(
        "General", "Business", "Health", "Science", "Sports", "Technology", "Entertainment"
    )

    // Use LaunchedEffect to observe changes in the current page of HorizontalPager
    LaunchedEffect(key1 = pagerState ){
        snapshotFlow { pagerState.currentPage }.collect { page ->
            // Notify the ViewModel about the category change when the page changes
            onEvent(NewsScreenEvent.onCategoryChanged(category = categories[page]))
        }
    }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            NewsScreenTopBar(
                scrollBehavior = scrollBehavior,
                onSearchIconClicked = {})}
    ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                // Display the CategoryTabRow to switch between categories
                CategoryTabRow(
                    pagerState = pagerState,
                    categories = categories,
                    onTabSelected =  { index ->
                    coroutineScope.launch { pagerState.animateScrollToPage(index) }
                }
                )
                // Use HorizontalPager to navigate between categories
                HorizontalPager(
                    count = categories.size,
                    state = pagerState
                ) {
                    // LazyColumn to efficiently handle large lists of items
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        // Iterate over the list of articles provided by the ViewModel
                        items(state.articles) { article ->
                            // Display each news article using the NewsArticleCard composable
                            NewsArticleCard(article = article, onCardClicked = {}
                            )
                        }
                    }
                }


            }

        }

    }

