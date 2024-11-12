package org.rawafedtech.marvelapp.presentation.viewstate

import org.rawafedtech.marvelapp.data.model.CharacterItem

data class ScreenState(
    val isLoading: Boolean = true,
    val items: List<CharacterItem> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 0
)