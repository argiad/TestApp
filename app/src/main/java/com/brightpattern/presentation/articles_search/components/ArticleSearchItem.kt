package com.brightpattern.presentation.articles_search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.brightpattern.common.Constants
import com.brightpattern.domain.model.Article
import com.brightpattern.presentation.articles_search.KeywordTagView
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun ArticleSearchItem(
    article: Article,
    onItemClick: () -> Unit
) {
    val path = article.multimedia.firstOrNull { it.type == "image" }?.url
    val url = Constants.IMAGE_URL + path

    val keyWords = article.keyWords.take(5)

    Card(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp).clickable(onClick = onItemClick),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 4.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = article.abstract,
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,

                    maxLines = 2
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 4.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(

                        contentScale = ContentScale.Crop,
                        painter = rememberImagePainter(url),
                        contentDescription = null,
                        modifier = Modifier
                            .size(120.dp, 90.dp)
                            .clip(RoundedCornerShape(4.dp)),
                    )

                }
                Column(
                    modifier = Modifier.padding(start = 6.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    {
                        Text(

                            text = article.snippet,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Justify,
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier.align(Alignment.CenterVertically),
                            maxLines = 25
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )
            {
                FlowRow(
                    mainAxisSpacing = 10.dp,
                    crossAxisSpacing = 10.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    keyWords.forEach { tag ->
                        KeywordTagView(tag = tag.value)
                    }
                }
            }
        }
    }

}