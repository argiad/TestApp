package com.brightpattern.presentation.articles_search

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brightpattern.common.Constants.STATE_KEY_LIST_POSITION
import com.brightpattern.common.Constants.STATE_KEY_PAGE
import com.brightpattern.common.Constants.STATE_KEY_QUERY
import com.brightpattern.common.Resource
import com.brightpattern.domain.model.Article
import com.brightpattern.domain.use_case.search.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    val page = mutableStateOf(1)
    val query = mutableStateOf("")

    private var listScrollPosition = 0

    private var job: Job? = null

    private fun getJob(): Job {
        return GlobalScope.launch {
            delay(2000)
            search()
        }
    }

    init {
        savedStateHandle.get<Int>(STATE_KEY_PAGE)?.let { p ->
            setPage(p)
        }
        savedStateHandle.get<String>(STATE_KEY_QUERY)?.let { q ->
            setQuery(q)
        }
        savedStateHandle.get<Int>(STATE_KEY_LIST_POSITION)?.let { p ->
            setListScrollPosition(p)
        }
    }

    fun updateQueryString(queryString: String) {
        job?.cancel()
        job = getJob()
        query.value = queryString
        job?.start()
    }

    fun nextPage() {
        if (!_state.value.isLoading) {
            commonSearch(page = page.value)
            setPage(page.value + 1)
        }
    }

    fun search() {
        onChangeScrollPosition(0)
        setPage(1)
        commonSearch()
    }

    private fun commonSearch(page: Int = 0) {

        searchUseCase(query = query.value, page = page).onEach { result ->

            val list = result.data ?: emptyList()
            val newList = if (page != 0) mutableListOf<Article>().apply { addAll(_state.value.articles); addAll(list) } else list

            when (result) {
                is Resource.Success -> {
                    _state.value = SearchState(
                        isLoading = false,
                        articles = newList
                    )
                    Log.d("Resource", "Success")
                }

                is Resource.Loading -> {
                    _state.value = SearchState(
                        isLoading = true,
                        articles = newList
                    )
                    Log.d("Resource", "Loading")
                }

                is Resource.Error -> {
                    _state.value = SearchState(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occurred"
                    )
                    Log.d("Resource", "Error \n ${state.value.error}")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onChangeScrollPosition(position: Int) {
        setListScrollPosition(position = position)
    }

    private fun setQuery(query: String) {
        this.query.value = query
        savedStateHandle.set(STATE_KEY_QUERY, query)
    }

    private fun setPage(page: Int) {
        this.page.value = page
        savedStateHandle.set(STATE_KEY_PAGE, page)
    }

    private fun setListScrollPosition(position: Int) {
        listScrollPosition = position
        savedStateHandle.set(STATE_KEY_LIST_POSITION, position)
    }

}