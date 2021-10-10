package com.brightpattern.domain.model

import com.brightpattern.data.remote.dto.Headline
import com.brightpattern.data.remote.dto.Keyword
import com.brightpattern.data.remote.dto.Multimedia

data class Article(
    val `abstract`: String,
    val headline: Headline,
    val id: String,
    val leadParagraph: String,
    val multimedia: List<Multimedia>,
    val keyWords: List<Keyword>,
    val pubDate: String,
    val uri: String,
    val webUrl: String,
    val snippet: String,
)
