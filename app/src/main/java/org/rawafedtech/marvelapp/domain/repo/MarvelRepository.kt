package org.rawafedtech.marvelapp.domain.repo

import kotlinx.coroutines.flow.Flow
import org.rawafedtech.marvelapp.data.model.CharachtersResponse
import org.rawafedtech.marvelapp.utils.NetworkResult


interface MarvelRepository {
    suspend fun searchCharacters(limit: Int? = null, title: String? = null,offset:Int): Flow<NetworkResult<CharachtersResponse>>
    suspend fun fetchComics(limit: Int? = null, charId: Int,offset:Int): Flow<NetworkResult<CharachtersResponse>>
    suspend fun fetchEvents(limit: Int? = null, charId: Int,offset:Int): Flow<NetworkResult<CharachtersResponse>>
    suspend fun fetchSeries(limit: Int? = null, charId: Int,offset:Int): Flow<NetworkResult<CharachtersResponse>>
    suspend fun fetchStories(limit: Int? = null, charId: Int,offset:Int): Flow<NetworkResult<CharachtersResponse>>
}
