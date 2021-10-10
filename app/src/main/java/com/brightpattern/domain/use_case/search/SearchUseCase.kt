package com.brightpattern.domain.use_case.search

import com.brightpattern.common.Resource
import com.brightpattern.domain.model.Article
import com.brightpattern.domain.repository.NYTRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: NYTRepo
) {

    operator fun invoke(query: String, page: Int = 0): Flow<Resource<List<Article>>> = flow {
        try {
            emit(Resource.Loading<List<Article>>())
            val list = repository.searchArticles(query = query, page = page)
            emit(Resource.Success<List<Article>>(list))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Article>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Article>>("Couldn't reach server. Check your internet connection."))
        }
    }
}