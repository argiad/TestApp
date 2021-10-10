package com.brightpattern.data.remote

import com.brightpattern.common.Constants
import com.brightpattern.data.remote.dto.NYTResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface NewYorkTimesAPI {

    @GET("search/v2/articlesearch.${Constants.REQUEST_TYPE}/?fq=type_of_material:(\"News\")&api-key=${Constants.API_KEY}")
    suspend fun searchArticles(
        @Query("q") query: String,
        @Query("page") page: Int = 0,
    ) : NYTResponse

    @GET("search/v2/articlesearch.${Constants.REQUEST_TYPE}/?api-key=${Constants.API_KEY}")
    suspend fun getArticle(
        @Query("fq") fq: String
    ): NYTResponse
}