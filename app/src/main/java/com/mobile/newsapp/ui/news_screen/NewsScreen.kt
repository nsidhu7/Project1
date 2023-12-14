package com.mobile.newsapp.ui.news_screen

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue



import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.mobile.newsapp.domain.model.Article
import com.mobile.newsapp.ui.component.BottomSheetContent
import com.mobile.newsapp.ui.component.CategoryTabRow
import com.mobile.newsapp.ui.component.NewsArticleCard
import com.mobile.newsapp.ui.component.NewsScreenTopBar
import com.mobile.newsapp.ui.component.RetryContent
import com.mobile.newsapp.ui.component.SearchAppBar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class, ExperimentalPagerApi::class
)
@Composable
fun NewsScreen(
    state: NewsScreenState,
    onEvent: (NewsScreenEvent) -> Unit,
    onReadFullStoryButtonClicked: (String) -> Unit
){
    // created value to provide Scroll Behavior
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val pagerState = com.google.accompanist.pager.rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val categories = listOf(
        "General", "Business", "Health", "Science", "Sports", "Technology", "Entertainment"
    )

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var shouldBottomSheetShow by remember {
        mutableStateOf(false)
    }
    if (shouldBottomSheetShow) {
        ModalBottomSheet(
            onDismissRequest = { shouldBottomSheetShow = false },
            sheetState = sheetState,
            content = {
                state.selectedArticle?.let {
                    BottomSheetContent(article = it,
                        onReadFullStoryButtonClicked = {
                            onReadFullStoryButtonClicked(it.url)
                            coroutineScope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) shouldBottomSheetShow = false
                            }
                        }
                    )
                }
            }
            )
    }

    // Use LaunchedEffect to observe changes in the current page of HorizontalPager
    LaunchedEffect(key1 = pagerState ){
        snapshotFlow { pagerState.currentPage }.collect { page ->
            // Notify the ViewModel about the category change when the page changes
            onEvent(NewsScreenEvent.OnCategoryChanged(category = categories[page]))
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Crossfade(targetState = state.isSearchBarVisible, label = "") { isVisible ->
            if(isVisible) {
                SearchAppBar(
                    value = "",
                    onValueChange = {},
                    onCloseIconClicked = { onEvent(NewsScreenEvent.OnCloseIconClicked)},
                    onSearchClicked = {}
                    )
                NewsArticlesList(
                    state = state,
                    onNewsCardClicked = { article ->
                        shouldBottomSheetShow = true
                        onEvent(NewsScreenEvent.OnNewsCardClicked(article = article))
                    },
                    onRetry = {
                        onEvent(NewsScreenEvent.OnCategoryChanged(state.category))
                    }
                )

            } else {
                //Using Scaffold to use NewsScreenTopBar on screen
                Scaffold(
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = {
                        NewsScreenTopBar(
                            scrollBehavior = scrollBehavior,
                            onSearchIconClicked = {
                                onEvent(NewsScreenEvent.OnSearchIconClicked)
                            })}
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
                            NewsArticlesList(
                                state = state,
                                onNewsCardClicked = { article ->
                                    shouldBottomSheetShow = true
                                    onEvent(NewsScreenEvent.OnNewsCardClicked(article = article))
                                },
                                onRetry = {
                                    onEvent(NewsScreenEvent.OnCategoryChanged(state.category))
                                }
                            )
                        }


                    }

                }
            }

        }
    }


    }
@Composable
fun NewsArticlesList(
    state: NewsScreenState,
    onNewsCardClicked: (Article) -> Unit,
    onRetry: () -> Unit
){
    // LazyColumn to efficiently handle large lists of items
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        // Iterate over the list of articles provided by the ViewModel
        items(state.articles) { article ->
            // Display each news article using the NewsArticleCard composable
            NewsArticleCard(
                article = article,
                onCardClicked = onNewsCardClicked
            )
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        if (state.isloading){
            CircularProgressIndicator()
        }
        if (state.error != null){
            RetryContent(error = state.error, onRetry = onRetry)
        }
    }
}
