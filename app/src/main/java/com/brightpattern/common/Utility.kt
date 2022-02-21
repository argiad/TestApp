package com.brightpattern.common

import android.content.Context
import com.brightpattern.data.remote.dto.HTTPResponse
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class Utility @Inject constructor(
    @ActivityContext private val context: Context,
    val gson: Gson
) {

    /**
     * Read local asset file
     *
     * @param assetName name of the asset to read
     * @return content of file in string format
     */
    fun readAsset(assetName: String): String {
        val json: String
        context.assets.open(assetName).use {
            json = it.readBytes().toString(Charsets.UTF_8)
        }
        return json
    }

    fun getMockData() : HTTPResponse {
        val mockStringData = readAsset(Constants.MOCK_DATA)
        return gson.fromJson(mockStringData, HTTPResponse::class.java)
    }
}