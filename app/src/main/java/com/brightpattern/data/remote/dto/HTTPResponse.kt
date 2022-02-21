package com.brightpattern.data.remote.dto


import com.google.gson.annotations.SerializedName

data class HTTPResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("response")
    val response: Response
)