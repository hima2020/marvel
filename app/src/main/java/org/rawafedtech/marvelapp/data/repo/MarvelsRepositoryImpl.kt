package org.rawafedtech.marvelapp.data.repo

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import org.rawafedtech.marvelapp.data.api.ApiService
import org.rawafedtech.marvelapp.data.model.CharachtersResponse
import org.rawafedtech.marvelapp.domain.repo.MarvelRepository
import org.rawafedtech.marvelapp.utils.NetworkResult


class MarvelsRepositoryImpl(
    private val marvelService: ApiService,
) : MarvelRepository {
    override suspend fun searchCharacters(
        limit: Int?,
        title: String?,
        offset: Int
    ) = flow<NetworkResult<CharachtersResponse>> {
        with(marvelService.fetchCharacters(limit, title, offset)) {
            if (isSuccessful) {
                emit(NetworkResult.Success(this.body()))
            } else {
                emit(NetworkResult.Error(this.errorBody().toString()))
            }
        }
    }.catch {
        emit(NetworkResult.Error(it.localizedMessage))


    }

    override suspend fun fetchComics(
        limit: Int?,
        charId: Int,
        offset: Int
    ) = flow<NetworkResult<CharachtersResponse>> {
        with(marvelService.fetchComics(charId, limit, offset)) {
            if (isSuccessful) {
                emit(NetworkResult.Success(this.body()))
            } else {
                emit(NetworkResult.Error(this.errorBody().toString()))
            }
        }
    }.catch {
        emit(NetworkResult.Error(it.localizedMessage))
    }

    override suspend fun fetchEvents(
        limit: Int?,
        charId: Int,
        offset: Int
    ) = flow<NetworkResult<CharachtersResponse>> {
        with(marvelService.fetchEvents(charId, limit, offset)) {
            if (isSuccessful) {
                emit(NetworkResult.Success(this.body()))
            } else {
                emit(NetworkResult.Error(this.errorBody().toString()))
            }
        }
    }.catch {
        emit(NetworkResult.Error(it.localizedMessage))
    }

    override suspend fun fetchSeries(
        limit: Int?,
        charId: Int,
        offset: Int
    ) = flow<NetworkResult<CharachtersResponse>> {
        with(marvelService.fetchSeries(charId, limit, offset)) {
            if (isSuccessful) {
                emit(NetworkResult.Success(this.body()))
            } else {
                emit(NetworkResult.Error(this.errorBody().toString()))
            }
        }
    }.catch {
        emit(NetworkResult.Error(it.localizedMessage))
    }
    override suspend fun fetchStories(
        limit: Int?,
        charId: Int,
        offset: Int
    ) = flow<NetworkResult<CharachtersResponse>> {
        with(marvelService.fetchStories(charId, limit, offset)) {
            if (isSuccessful) {
                emit(NetworkResult.Success(this.body()))
            } else {
                emit(NetworkResult.Error(this.errorBody().toString()))
            }
        }
    }.catch {
        emit(NetworkResult.Error(it.localizedMessage))
    }
}