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
import androidx.navigation.compose.rememberNavController
import com.mobile.newsapp.ui.news_screen.NewsScreen
import com.mobile.newsapp.ui.news_screen.NewsScreenViewModel
import com.mobile.newsapp.ui.theme.NewsAppTheme
import com.mobile.newsapp.util.NavGraphSetup
import dagger.hilt.android.AndroidEntryPoint

// AndroidEntryPoint annotation for Dagger Hilt integration
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set up the content using the NewsAppTheme
        setContent {
            NewsAppTheme {
                // Remember the navigation controller to manage navigation
                val navController = rememberNavController()
                // Set up the navigation graph
                NavGraphSetup(navController = navController)
            }
        }
    }
}
