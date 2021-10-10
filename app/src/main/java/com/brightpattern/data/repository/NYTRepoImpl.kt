package com.brightpattern.data.repository

import com.brightpattern.data.remote.NewYorkTimesAPI
import com.brightpattern.data.remote.dto.toArticle
import com.brightpattern.domain.model.Article
import com.brightpattern.domain.repository.NYTRepo
import javax.inject.Inject

class NYTRepoImpl @Inject constructor(
    private val api: NewYorkTimesAPI
) : NYTRepo {

    override suspend fun searchArticles(query: String, page: Int): List<Article> {
        return api.searchArticles(query = query, page = page).response.docs.map { it.toArticle() }
    }

    override suspend fun getArticleBy(id: String): Article? {
        val filter = "uri:(\"nyt://article/$id\")"
        return api.getArticle(filter).response.docs.firstOrNull()?.toArticle()
    }


}