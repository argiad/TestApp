package com.brightpattern.presentation.articles_search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.brightpattern.common.Constants
import com.brightpattern.presentation.Screen
import com.brightpattern.presentation.articles_search.components.ArticleSearchItem
import com.brightpattern.presentation.articles_search.components.SearchView

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Scaffold(
        topBar = {
            SearchView(
                query = viewModel.query.value,
                onQueryChanged = {
//                    viewModel.query.value = it
                    viewModel.updateQueryString(it)
                },
                onExecuteSearch = viewModel::search
            )
        }) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(state.articles) { index, article ->
                    viewModel.onChangeScrollPosition(index)
                    if ((index + 1) >= (viewModel.page.value * Constants.REQUEST_PAGE_SIZE))
                        viewModel.nextPage()
                    ArticleSearchItem(
                        article = article,
                        onItemClick = {
                            navController.navigate(Screen.ArticleDetailScreen.route + "/${article.id}")
                        }
                    )
                }
            }
            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}