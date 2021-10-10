package com.brightpattern.data.remote.dto


import com.google.gson.annotations.SerializedName

data class NYTResponse(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("response")
    val response: Response,
    @SerializedName("status")
    val status: String
)