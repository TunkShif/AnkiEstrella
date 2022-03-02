package one.tunkshif.ankiestrella.ui.query

import one.tunkshif.ankiestrella.data.model.Word

sealed class QueryUiState {
    data class StartLoading(val query: String) : QueryUiState()
    object NoSchemaError : QueryUiState()
}

sealed class QueryState {
    data class Loaded(val word: Word) : QueryState()
    object Loading : QueryState()
    object NetworkError : QueryState()
    object NotFoundError : QueryState()
}