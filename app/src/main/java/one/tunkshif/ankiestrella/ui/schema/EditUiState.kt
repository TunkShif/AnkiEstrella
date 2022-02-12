package one.tunkshif.ankiestrella.ui.schema

import androidx.compose.runtime.*
import one.tunkshif.ankiestrella.data.SourceRegistry
import one.tunkshif.ankiestrella.data.model.Schema
import one.tunkshif.ankiestrella.ui.composable.TextFieldState
import one.tunkshif.ankiestrella.util.AnkiDroidHelper

class EditUiState {
    val decks = mutableStateListOf<String>()
    val models = mutableStateListOf<String>()
    val sources = mutableStateListOf<String>()
    val sourceFields = mutableStateListOf<String>()
    val modelFields = mutableStateListOf<String>()
    var hasConflict by mutableStateOf(false)
    var isModelSelected by mutableStateOf(false)
        private set

    init {
        decks.addAll(AnkiDroidHelper.getAllDecks())
        models.addAll(AnkiDroidHelper.getAllModels())
        sources.addAll(SourceRegistry.all().map { it.name })
    }

    fun onSourceSelected(name: String) {
        val source = SourceRegistry.getByName(name)
        source?.let { dict ->
            sourceFields.clear()
            sourceFields.addAll(dict.availableFields.map { it.toString() })
        }
    }

    fun onModelSelected(name: String) {
        isModelSelected = true
        AnkiDroidHelper.getModelFields(name)?.let {
            modelFields.clear()
            modelFields.addAll(it)
        }
    }
}

class EditInputState {
    var schemaNameState = TextFieldState()
    var deckName by mutableStateOf("")
    var sourceName by mutableStateOf("")
    var modelName by mutableStateOf("")
    val fieldMapping = mutableStateMapOf<String, String>()

    val isValid
        get() =
            listOf(schemaNameState.text, deckName, sourceName, modelName).all { it.isNotEmpty() }

    fun onFieldMappingChange(modelField: String, sourceField: String) {
        fieldMapping[modelField] = sourceField
    }

    fun toSchema() = Schema(
        name = schemaNameState.text,
        source = SourceRegistry.getByName(sourceName)?.id ?: "unknown",
        deck = deckName,
        model = modelName,
        fieldMapping = fieldMapping.toMap()
    )
}