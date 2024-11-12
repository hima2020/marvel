package org.rawafedtech.marvelapp.domain.interceptor

import kotlinx.coroutines.flow.Flow
import org.rawafedtech.marvelapp.data.model.CharachtersResponse
import org.rawafedtech.marvelapp.utils.NetworkResult

interface GetSeriesUseCase {

    suspend fun getSeries(id: Int, offset: Int): Flow<NetworkResult<CharachtersResponse>>
}