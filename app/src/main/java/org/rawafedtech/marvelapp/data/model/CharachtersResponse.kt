package org.rawafedtech.marvelapp.data.model

import com.google.gson.annotations.SerializedName

data class CharachtersResponse(
    @SerializedName("data")
    val data: Data?,
)