package com.brightpattern.presentation

sealed class Screen(val route: String) {
    object ArticleSearchScreen: Screen("article_search_screen")
    object ArticleDetailScreen: Screen("article_detail_screen")
}