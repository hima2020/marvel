package org.rawafedtech.marvelapp.data.interceptor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.rawafedtech.marvelapp.data.model.CharachtersResponse
import org.rawafedtech.marvelapp.domain.interceptor.GetComicsUseCase
import org.rawafedtech.marvelapp.domain.repo.MarvelRepository
import org.rawafedtech.marvelapp.utils.NetworkResult

class GetComicsUseCaseImpl(private val repo: MarvelRepository) : GetComicsUseCase {
    override suspend fun getComics(id: Int, offset: Int): Flow<NetworkResult<CharachtersResponse>> =
        flow {
            repo.fetchComics(10, id, offset).collect { response ->
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

