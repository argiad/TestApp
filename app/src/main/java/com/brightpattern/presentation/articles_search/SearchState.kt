package com.brightpattern.presentation.articles_search

import com.brightpattern.domain.model.Article

data class SearchState(
    val isLoading: Boolean = true,
    val articles: List<Article> = emptyList(),
    val error: String = "",
)