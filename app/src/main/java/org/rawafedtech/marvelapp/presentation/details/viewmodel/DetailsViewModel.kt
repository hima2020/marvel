package org.rawafedtech.marvelapp.presentation.details.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.rawafedtech.marvelapp.data.model.CharacterItem
import org.rawafedtech.marvelapp.domain.interceptor.GetComicsUseCase
import org.rawafedtech.marvelapp.domain.interceptor.GetEventsUseCase
import org.rawafedtech.marvelapp.domain.interceptor.GetSeriesUseCase
import org.rawafedtech.marvelapp.domain.interceptor.GetStoriesUseCase
import org.rawafedtech.marvelapp.presentation.viewstate.ScreenState
import org.rawafedtech.marvelapp.utils.DefaultPaginator
import org.rawafedtech.marvelapp.utils.NetworkResult
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val comicsUseCase: GetComicsUseCase,
    private val eventsUseCase: GetEventsUseCase,
    private val seriesUseCase: GetSeriesUseCase,
    private val storiesUseCase: GetStoriesUseCase,


    ) :
    ViewModel() {

    private var selectedId = 0
    fun setCharId(id: Int) {
        selectedId = id
    }

    var stateComicsMarvel by mutableStateOf(ScreenState())
    private val comicsPaginator: DefaultPaginator<Int, CharacterItem> = DefaultPaginator(
        initialKey = stateComicsMarvel.page,
        onLoadUpdated = {
            stateComicsMarvel = stateComicsMarvel.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            var result: Result<List<CharacterItem>> = Result.success(emptyList())
            comicsUseCase.getComics(selectedId, nextPage).collect { response ->
                result = when (response) {
                    is NetworkResult.Success -> {
                        Result.success(
                            response.data?.data?.characterItems
                                ?: listOf()
                        )
                    }

                    else -> {
                        Result.failure(Throwable(""))
                    }
                }
            }
            result

        },
        getNextKey = {
            stateComicsMarvel.page + 1
        },
        onError = {
            stateComicsMarvel = stateComicsMarvel.copy(error = "Error")
        },
        onSuccess = { items, newKey ->
            stateComicsMarvel = stateComicsMarvel.copy(
                items = stateComicsMarvel.items + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )


    var stateSeriesMarvel by mutableStateOf(ScreenState())

    private val seriesPaginator: DefaultPaginator<Int, CharacterItem> = DefaultPaginator(
        initialKey = stateSeriesMarvel.page,
        onLoadUpdated = {
            stateSeriesMarvel = stateSeriesMarvel.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            var result: Result<List<CharacterItem>> = Result.success(emptyList())
            seriesUseCase.getSeries(selectedId, nextPage).collect { response ->
                result = when (response) {
                    is NetworkResult.Success -> {
                        Result.success(
                            response.data?.data?.characterItems
                                ?: listOf()
                        )
                    }

                    else -> {
                        Result.failure(Throwable(""))
                    }
                }
            }
            result

        },
        getNextKey = {
            stateSeriesMarvel.page + 1
        },
        onError = {
            stateSeriesMarvel = stateSeriesMarvel.copy(error = "Error")
        },
        onSuccess = { items, newKey ->
            stateSeriesMarvel = stateSeriesMarvel.copy(
                items = stateSeriesMarvel.items + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )


    fun loadSeriesNextItems() {
        viewModelScope.launch {
            seriesPaginator.loadNextItems()
        }
    }


    var stateEventsMarvel by mutableStateOf(ScreenState())
    private val eventsPaginator: DefaultPaginator<Int, CharacterItem> = DefaultPaginator(
        initialKey = stateEventsMarvel.page,
        onLoadUpdated = {
            stateEventsMarvel = stateEventsMarvel.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            var result: Result<List<CharacterItem>> = Result.success(emptyList())
            eventsUseCase.getEvents(selectedId, nextPage).collect { response ->
                result = when (response) {
                    is NetworkResult.Success -> {
                        Result.success(
                            response.data?.data?.characterItems
                                ?: listOf()
                        )
                    }

                    else -> {
                        Result.failure(Throwable(""))
                    }
                }
            }
            result

        },
        getNextKey = {
            stateEventsMarvel.page + 1
        },
        onError = {
            stateEventsMarvel = stateEventsMarvel.copy(error = "Error")
        },
        onSuccess = { items, newKey ->
            stateEventsMarvel = stateEventsMarvel.copy(
                items = stateEventsMarvel.items + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )


    fun loadEventsNextItems() {
        viewModelScope.launch {
            eventsPaginator.loadNextItems()
        }
    }


    fun loadComicsNextItems() {
        viewModelScope.launch {
            comicsPaginator.loadNextItems()
        }
    }


    var stateStoriesMarvel by mutableStateOf(ScreenState())

    private val storiesPaginator: DefaultPaginator<Int, CharacterItem> = DefaultPaginator(
        initialKey = stateStoriesMarvel.page,
        onLoadUpdated = {
            stateStoriesMarvel = stateStoriesMarvel.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            var result: Result<List<CharacterItem>> = Result.success(emptyList())
            storiesUseCase.getStories(selectedId, nextPage).collect { response ->
                result = when (response) {
                    is NetworkResult.Success -> {
                        Result.success(
                            response.data?.data?.characterItems
                                ?: listOf()
                        )
                    }

                    else -> {
                        Result.failure(Throwable(""))
                    }
                }
            }
            result

        },
        getNextKey = {
            stateStoriesMarvel.page + 1
        },
        onError = {
            stateStoriesMarvel = stateStoriesMarvel.copy(error ="Error")
        },
        onSuccess = { items, newKey ->
            stateStoriesMarvel = stateStoriesMarvel.copy(
                items = stateStoriesMarvel.items + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )


    fun loadStoriesNextItems() {
        viewModelScope.launch {
            storiesPaginator.loadNextItems()
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            comicsPaginator.loadNextItems()
            seriesPaginator.loadNextItems()
            storiesPaginator.loadNextItems()
            eventsPaginator.loadNextItems()
        }
    }


}
