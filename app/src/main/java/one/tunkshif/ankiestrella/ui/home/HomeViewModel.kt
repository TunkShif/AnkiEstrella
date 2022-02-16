package one.tunkshif.ankiestrella.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import one.tunkshif.ankiestrella.data.SourceRegistry
import one.tunkshif.ankiestrella.data.model.Schema
import one.tunkshif.ankiestrella.data.repository.SchemaRepository
import one.tunkshif.ankiestrella.ui.destinations.EditorScreenDestination
import one.tunkshif.ankiestrella.ui.navigation.NavEvent
import one.tunkshif.ankiestrella.util.AnkiDroidHelper

class HomeViewModel(
    private val schemaRepository: SchemaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loaded())
    val uiState: StateFlow<HomeUiState> = _uiState

    private val _navigateTo = MutableStateFlow<NavEvent?>(null)
    val navigateTo: StateFlow<NavEvent?> = _navigateTo

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

    fun onFabClicked() {
        _navigateTo.value = NavEvent(EditorScreenDestination())
    }

    fun onEditSchemaClicked(schema: Schema) {
        _navigateTo.value = NavEvent(EditorScreenDestination(schema = schema))
    }
}