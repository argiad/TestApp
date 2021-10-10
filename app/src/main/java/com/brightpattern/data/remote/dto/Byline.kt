package com.brightpattern.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Byline(
    @SerializedName("organization")
    val organization: Any?,
    @SerializedName("original")
    val original: String?,
    @SerializedName("person")
    val person: List<Person>
)