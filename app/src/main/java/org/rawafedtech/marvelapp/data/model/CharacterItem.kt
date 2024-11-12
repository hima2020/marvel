package org.rawafedtech.marvelapp.data.model

import com.google.gson.annotations.SerializedName


data class CharacterItem(

    @SerializedName("description")
    val description: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail?,
)