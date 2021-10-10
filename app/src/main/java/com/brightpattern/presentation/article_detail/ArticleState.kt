package com.brightpattern.presentation.article_detail

import com.brightpattern.domain.model.Article

data class ArticleState (val isLoading: Boolean = true,
                         val article: Article? = null,
                         val error: String = "",)