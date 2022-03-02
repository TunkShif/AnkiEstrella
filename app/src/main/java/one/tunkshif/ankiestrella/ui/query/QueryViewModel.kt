package one.tunkshif.ankiestrella.ui.query

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import one.tunkshif.ankiestrella.data.model.Schema
import one.tunkshif.ankiestrella.data.repository.SchemaRepository

class QueryViewModel(
    val query: String,
    private val schemaRepository: SchemaRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<QueryUiState>(QueryUiState.NoSchemaError)
    val uiState: StateFlow<QueryUiState> = _uiState

    val schemas = mutableStateListOf<Schema>()

    init {
        viewModelScope.launch {
            schemaRepository.getAll().collect {
                if (it.isEmpty()) {
                    _uiState.update { QueryUiState.NoSchemaError }
                } else {
                    schemas.addAll(it)
                    _uiState.update { QueryUiState.StartLoading(query) }
                }
            }
        }
    }
}