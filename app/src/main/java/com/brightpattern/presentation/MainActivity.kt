package com.brightpattern.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brightpattern.common.Constants
import com.brightpattern.presentation.article_detail.ArticleDetailScreen
import com.brightpattern.presentation.articles_search.SearchScreen
import com.brightpattern.presentation.ui.theme.NYTTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NYTTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ArticleSearchScreen.route
                    ) {
                        composable(
                            route = Screen.ArticleSearchScreen.route
                        ) {
                            SearchScreen(navController)
                        }
                        composable(
                            route = Screen.ArticleDetailScreen.route + "/{${Constants.PARAM_ARTICLE_ID}}"
                        ) {
                            ArticleDetailScreen(navController)
                        }
                    }
                }
            }
        }
    }
}