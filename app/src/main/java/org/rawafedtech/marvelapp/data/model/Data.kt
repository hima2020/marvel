package org.rawafedtech.marvelapp.data.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("results")
    val characterItems: List<CharacterItem>?,
    @SerializedName("total")
    val total: Int?
)