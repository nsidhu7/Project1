package com.mobile.newsapp.util

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mobile.newsapp.ui.article_screen.ArticleScreen
import com.mobile.newsapp.ui.news_screen.NewsScreen
import com.mobile.newsapp.ui.news_screen.NewsScreenViewModel

@Composable
fun NavGraphSetup(
    navController: NavHostController
) {
    // Key for the URL argument passed to the ArticleScreen
    val argKey = "web_url"

    NavHost(
        navController = navController,
        startDestination = "news_screen"
    ) {
        // Composable for the NewsScreen
        composable(route = "news_screen") {
            // Retrieve the NewsScreenViewModel using Hilt
            val viewModel: NewsScreenViewModel = hiltViewModel()

            // Display the NewsScreen composable with the ViewModel
            NewsScreen(
                // Pass the state and event callback to NewsScreen
                state = viewModel.state,
                onEvent = viewModel::onEvent,
                onReadFullStoryButtonClicked = { url ->
                    // Navigate to ArticleScreen with the URL as a query parameter
                    navController.navigate("article_screen?$argKey=$url")
                }
            )
        }

        // Composable for the ArticleScreen with a URL argument
        composable(
            route = "article_screen?$argKey={$argKey}",
            arguments = listOf(navArgument(name = argKey) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            // Retrieve the URL argument from the back stack entry
            val url = backStackEntry.arguments?.getString(argKey)

            // Display the ArticleScreen composable with the URL and back navigation callback
            ArticleScreen(
                url = url,
                onBackPressed = {
                    // Navigate back when the back button is clicked
                    navController.navigateUp()
                }
            )
        }
    }
}
