package com.brightpattern.domain.repository

import com.brightpattern.domain.model.Article

interface NYTRepo {
    suspend fun searchArticles(query: String, page: Int = 0): List<Article>
    suspend fun getArticleBy(id: String): Article?
}