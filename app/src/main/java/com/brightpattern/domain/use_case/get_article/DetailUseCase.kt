package com.brightpattern.domain.use_case.get_article

import com.brightpattern.common.Resource
import com.brightpattern.domain.model.Article
import com.brightpattern.domain.repository.NYTRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DetailUseCase @Inject constructor(
    private val repository: NYTRepo
) {
    operator fun invoke(articleId: String): Flow<Resource<Article?>> = flow {
        try {
            emit(Resource.Loading<Article?>())
            val article = repository.getArticleBy(articleId)
            emit(Resource.Success<Article?>(article))
        } catch (e: HttpException) {
            emit(Resource.Error<Article?>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<Article?>("Couldn't reach server. Check your internet connection."))
        }
    }
}