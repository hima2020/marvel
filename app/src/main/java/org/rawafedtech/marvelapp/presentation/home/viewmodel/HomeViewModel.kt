package org.rawafedtech.marvelapp.presentation.home.viewmodel

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
import org.rawafedtech.marvelapp.domain.interceptor.GetMarvelsUseCase
import org.rawafedtech.marvelapp.presentation.viewstate.ScreenState
import org.rawafedtech.marvelapp.utils.DefaultPaginator
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val marvelCharsUseCase: GetMarvelsUseCase) :
    ViewModel() {
    var statePaging by mutableStateOf(ScreenState())
    private val paginator: DefaultPaginator<Int, CharacterItem> = DefaultPaginator(
        initialKey = statePaging.page,
        onLoadUpdated = {
            statePaging = statePaging.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            Result.success(
                marvelCharsUseCase.getMarvels(null, nextPage).first().data?.data?.characterItems
                    ?: listOf()
            )

        },
        getNextKey = {
            statePaging.page + 1
        },
        onError = {
            statePaging = statePaging.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newKey ->
            statePaging = statePaging.copy(
                items = statePaging.items + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )

    init {
        viewModelScope.launch(Dispatchers.IO) {
            paginator.loadNextItems()
        }
    }

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

}