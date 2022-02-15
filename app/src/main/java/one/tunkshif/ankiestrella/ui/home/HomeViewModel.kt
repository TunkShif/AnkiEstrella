package one.tunkshif.ankiestrella.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import one.tunkshif.ankiestrella.data.SourceRegistry
import one.tunkshif.ankiestrella.data.repository.SchemaRepository
import one.tunkshif.ankiestrella.util.AnkiDroidHelper

class HomeViewModel(
    private val schemaRepository: SchemaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loaded())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        viewModelScope.launch {
            val deckCount = AnkiDroidHelper.getAllDecks().count()
            val dictCount = SourceRegistry.count
            schemaRepository.getAll().collect { schemas ->
                if (schemas.isEmpty()) {
                    _uiState.value = HomeUiState.Empty
                } else {
                    _uiState.value = HomeUiState.Loaded(schemas, deckCount, dictCount)
                }
            }
        }
    }
}