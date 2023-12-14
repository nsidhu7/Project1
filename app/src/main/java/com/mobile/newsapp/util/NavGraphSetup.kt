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
){
    val argKey = "web_url"
    NavHost(navController = navController,
        startDestination = "news_screen"
    ){
        composable(route = "news_screen"){
           // Retrieve the NewsScreenViewModel using Hilt
            val viewModel: NewsScreenViewModel = hiltViewModel()
            // Display the NewsScreen composable with the ViewModel
            NewsScreen(
                // Pass the state and event callback to NewsScreen
                state = viewModel.state,
                onEvent = viewModel::onEvent,
                onReadFullStoryButtonClicked = { url ->
                    navController.navigate("article_screen?$argKey=$url")
                }
            )
        }
        composable(
            route = "article_screen?$argKey={$argKey}",
            arguments = listOf(navArgument(name = argKey){
                type = NavType.StringType
            })
        ){ backStackEntry ->
                ArticleScreen(
                    url = backStackEntry.arguments?.getString(argKey),
                    onBackPressed = {
                        navController.navigateUp()
                    }
                )
            }
    }
}