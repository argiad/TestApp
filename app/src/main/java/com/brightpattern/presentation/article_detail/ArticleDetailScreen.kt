package com.brightpattern.presentation.article_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.brightpattern.common.Constants
import com.brightpattern.presentation.articles_search.KeywordTagView
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun ArticleDetailScreen(
    navController: NavController,
    viewModel: ArticleDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val images = state.article?.multimedia?.filter { it.type == "image" && it.subtype == "blog480" }?.map { Constants.IMAGE_URL + it.url } ?: emptyList()


    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            elevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
        ) {
            Column {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp, top = 4.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = state.article?.abstract ?: "> **** <",
                        style = typography.body1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                    )
                }

                LazyRow(
                    modifier = Modifier
                        .wrapContentHeight()
                ) {
                    itemsIndexed(images) { _, url ->
                        Card(
                            backgroundColor = Color.LightGray,
                            modifier = Modifier
                                .size(240.dp, 160.dp)
                                .padding(4.dp)
                        ) {

                            Image(
                                contentScale = ContentScale.Crop,
                                painter = rememberImagePainter(url),
                                contentDescription = null,
                            )

                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                )
                {
                    Text(

                        text = state.article?.snippet ?: " __ ___ __",
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Justify,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.align(Alignment.CenterVertically),
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 20.dp)
                )
                {
                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        state.article?.keyWords?.forEach { tag ->
                            KeywordTagView(tag = tag.value)
                        }
                    }
                }
            }
        }
    }
}
