package org.rawafedtech.marvelapp.domain.interceptor

import kotlinx.coroutines.flow.Flow
import org.rawafedtech.marvelapp.data.model.CharachtersResponse
import org.rawafedtech.marvelapp.utils.NetworkResult

interface GetComicsUseCase {
    suspend fun getComics(id:Int,offset:Int): Flow<NetworkResult<CharachtersResponse>>
}