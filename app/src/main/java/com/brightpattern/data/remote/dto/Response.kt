package com.brightpattern.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("docs")
    val docs: List<Doc>,
    @SerializedName("meta")
    val meta: Meta
)