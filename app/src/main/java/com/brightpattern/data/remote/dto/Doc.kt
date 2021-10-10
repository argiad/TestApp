package com.brightpattern.data.remote.dto


import android.net.Uri
import com.brightpattern.domain.model.Article
import com.google.gson.annotations.SerializedName
import java.net.URI
import kotlin.io.path.toPath

data class Doc(
    @SerializedName("abstract")
    val `abstract`: String,
    @SerializedName("byline")
    val byline: Byline,
    @SerializedName("document_type")
    val documentType: String,
    @SerializedName("headline")
    val headline: Headline,
    @SerializedName("_id")
    val id: String,
    @SerializedName("keywords")
    val keywords: List<Keyword>,
    @SerializedName("lead_paragraph")
    val leadParagraph: String,
    @SerializedName("multimedia")
    val multimedia: List<Multimedia>,
    @SerializedName("news_desk")
    val newsDesk: String,
    @SerializedName("pub_date")
    val pubDate: String,
    @SerializedName("section_name")
    val sectionName: String,
    @SerializedName("snippet")
    val snippet: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("subsection_name")
    val subsectionName: String,
    @SerializedName("type_of_material")
    val typeOfMaterial: String,
    @SerializedName("uri")
    val uri: String,
    @SerializedName("web_url")
    val webUrl: String,
    @SerializedName("word_count")
    val wordCount: Int
)

fun Doc.toArticle(): Article {
    return Article(
        abstract = abstract,
        headline = headline,
        id = Uri.parse(id).lastPathSegment ?: "",
        multimedia = multimedia,
        leadParagraph = leadParagraph,
        pubDate = pubDate,
        uri = uri,
        webUrl = webUrl,
        keyWords = keywords,
        snippet = snippet
    )
}