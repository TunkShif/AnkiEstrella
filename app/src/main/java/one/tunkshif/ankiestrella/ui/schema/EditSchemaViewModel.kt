package one.tunkshif.ankiestrella.ui.schema

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import one.tunkshif.ankiestrella.data.SourceRegistry
import one.tunkshif.ankiestrella.data.repository.SchemaRepository

class EditSchemaViewModel(
    private val schemaRepository: SchemaRepository
) : ViewModel() {
    val uiState = EditUiState()
    val inputState = EditInputState()

    suspend fun hasConflict(): Boolean {
        var hasConflict = false
        val name = inputState.schemaNameState.text
        val schema = viewModelScope.async {
            schemaRepository.getOne(name)
        }
        schema.await()?.let { hasConflict = true }
        uiState.hasConflict = hasConflict
        return hasConflict
    }

    fun save() {
        val schema = inputState.toSchema()
        viewModelScope.launch {
            schemaRepository.save(schema)
        }
    }

    fun update() {
        // TODO: Error handling
        viewModelScope.launch {
            val name = inputState.schemaNameState.text
            val schema = schemaRepository.getOne(name)
            schema?.copy(
                source = SourceRegistry.getByName(inputState.sourceName)?.id ?: "unknown",
                deck = inputState.deckName,
                model = inputState.modelName,
                fieldMapping = inputState.fieldMapping.toMap()
            )?.let { schemaRepository.update(it) }
        }
    }
}