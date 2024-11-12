package org.rawafedtech.marvelapp.data.interceptor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.rawafedtech.marvelapp.data.model.CharachtersResponse
import org.rawafedtech.marvelapp.domain.interceptor.GetMarvelsUseCase
import org.rawafedtech.marvelapp.domain.repo.MarvelRepository
import org.rawafedtech.marvelapp.utils.NetworkResult

class GetMarvelsUseCaseImpl(private val repo: MarvelRepository) : GetMarvelsUseCase {
    override suspend fun getMarvels(name: String?,offset:Int): Flow<NetworkResult<CharachtersResponse>> = flow {
        repo.searchCharacters(10, name,offset).collect { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val marvels = response.data
                    emit(NetworkResult.Success(marvels))
                }

                is NetworkResult.Error -> {
                    emit(NetworkResult.Error("Cannot get the Data "))
                }

                is NetworkResult.Loading -> {
                    emit(NetworkResult.Loading())
                }
            }
        }
    }
}

