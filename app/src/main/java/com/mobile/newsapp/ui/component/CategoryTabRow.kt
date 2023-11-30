package com.mobile.newsapp.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun CategoryTabRow(
    pagerState: com.google.accompanist.pager.PagerState,
    categories: List<String>,
    onTabSelected: (Int) -> Unit
){

    ScrollableTabRow(
        // Set the selected tab index based on the pager state
        selectedTabIndex = pagerState.currentPage,

        // Customize the appearance of the tab row
        edgePadding = 0.dp,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ) {
        // Iterate through the list of categories to create tabs
        categories.forEachIndexed { index, category ->
            Tab(
                // Determine if the current tab is selected based on the pager state
                selected = pagerState.currentPage == index,
                // Handle tab click to notify the parent about the selected tab index
                onClick = { onTabSelected(index) },
                // Provide the content for the tab
                content = {
                    //content is simple text for every single category
                    Text(
                        text = category,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 2.dp))
                }
                )
        }
    }
}