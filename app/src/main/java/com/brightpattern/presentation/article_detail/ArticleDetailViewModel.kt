package com.brightpattern.presentation.article_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brightpattern.common.Constants
import com.brightpattern.common.Resource
import com.brightpattern.domain.use_case.get_article.DetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    private val detailUseCase: DetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(ArticleState())
    val state: State<ArticleState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_ARTICLE_ID)?.let { articleId ->
            getArticle(articleId)
        }
    }

    private fun getArticle(id: String) {
        detailUseCase(articleId = id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        _state.value = ArticleState(isLoading = false, article = it )
                    }
                }
                is Resource.Loading -> {
                    _state.value = ArticleState(isLoading = true, article = null )
                    Log.e("detailUseCase", "Loading")
                }
                is Resource.Error -> {
                    _state.value = ArticleState(isLoading = false, article = null )
                    Log.e("detailUseCase", "Error ${result.message}")
                }
            }
        }.launchIn(viewModelScope)
    }

}