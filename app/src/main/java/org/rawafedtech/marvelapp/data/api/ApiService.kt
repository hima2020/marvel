package org.rawafedtech.marvelapp.data.api

import org.rawafedtech.marvelapp.data.model.CharachtersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("characters")
    suspend fun fetchCharacters(
        @Query("limit") limit: Int?,
        @Query("nameStartsWith") title: String?,
        @Query("offset") offset: Int?,

        ): Response<CharachtersResponse>

    @GET("characters/{Id}/comics")
    suspend fun fetchComics(
        @Path("Id") charachterID: Int?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,

        ): Response<CharachtersResponse>


    @GET("characters/{Id}/events")
    suspend fun fetchEvents(
        @Path("Id") charachterID: Int?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
    ): Response<CharachtersResponse>


    @GET("characters/{Id}/series")
    suspend fun fetchSeries(
        @Path("Id") charachterID: Int?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
    ): Response<CharachtersResponse>

    @GET("characters/{Id}/stories")
    suspend fun fetchStories(
        @Path("Id") charachterID: Int?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
    ): Response<CharachtersResponse>
}