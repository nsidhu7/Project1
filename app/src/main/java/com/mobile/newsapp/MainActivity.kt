package com.mobile.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.mobile.newsapp.ui.news_screen.NewsScreen
import com.mobile.newsapp.ui.news_screen.NewsScreenViewModel
import com.mobile.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                // Retrieve the NewsScreenViewModel using Hilt
                val viewModel: NewsScreenViewModel = hiltViewModel()
                // Display the NewsScreen composable with the ViewModel
                NewsScreen(
                    // Pass the state and event callback to NewsScreen
                    state = viewModel.state,
                    onEvent = viewModel::onEvent
                )

            }
        }
    }
}

