package one.tunkshif.ankiestrella.ui.home

import one.tunkshif.ankiestrella.data.model.Schema

sealed class HomeUiState {
    object Error : HomeUiState()
    object Empty : HomeUiState() // do permission check on home screen launch?
    data class Loaded(
        val schemas: List<Schema> = emptyList(),
        val deckCount: Int = 0,
        val dictCount: Int = 0
    ) : HomeUiState()
}