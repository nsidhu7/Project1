package com.mobile.newsapp.ui.component

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreenTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onSearchIconClicked: () -> Unit
){
    TopAppBar(
        // Pass the scroll behavior to the TopAppBar
        scrollBehavior = scrollBehavior,
        // Set the title of the TopAppBar
        title = { Text(text = "News Application", fontWeight = FontWeight.Bold) },
        // Define the actions in the app bar
        actions = {
            // Use IconButton for clickable icon button
            IconButton(onClick = onSearchIconClicked) {
                // Use the Icons.Default.Search vector for the search icon
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        },
        // Customize the colors of the app bar
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        )

    )
}