package one.tunkshif.ankiestrella.ui.editor

import androidx.compose.runtime.*
import one.tunkshif.ankiestrella.data.SourceRegistry
import one.tunkshif.ankiestrella.data.model.Schema
import one.tunkshif.ankiestrella.ui.composable.AutoCompleteTextFieldState
import one.tunkshif.ankiestrella.ui.composable.TextFieldState
import one.tunkshif.ankiestrella.util.AnkiDroidHelper

class EditorFormState(schema: Schema? = null) {
    var schemaId by mutableStateOf(0)
    val schemaName = TextFieldState()
    val deckName = AutoCompleteTextFieldState()
    val sourceName = AutoCompleteTextFieldState()
    val modelName = AutoCompleteTextFieldState()
    val sourceFields = mutableStateListOf<String>()
    val modelFields = mutableStateListOf<String>()
    val fieldMapping = mutableStateMapOf<String, String>()
    var hasConflict by mutableStateOf(false)
    var isModelSelected by mutableStateOf(false)
        private set

    val isValid
        get() =
            listOf(
                schemaName.text,
                deckName.selected,
                sourceName.selected,
                modelName.selected
            ).all { it.isNotEmpty() }

    init {
        val decks = AnkiDroidHelper.getAllDecks()
        val models = AnkiDroidHelper.getAllModels()
        val sources = SourceRegistry.all().map { it.name }
        deckName.onItemsChange(decks)
        sourceName.onItemsChange(sources)
        modelName.onItemsChange(models)

        schema?.let {
            schemaName.text = it.name
            deckName.selected = it.deck
            sourceName.selected = SourceRegistry.getById(it.source)?.name ?: ""
            modelName.selected = it.model
            fieldMapping.clear()
            fieldMapping.putAll(it.fieldMapping)
        }
    }

    fun onDeckSelected(name: String) {
        deckName.selected = name
    }

    fun onSourceSelected(name: String) {
        sourceName.selected = name
        val source = SourceRegistry.getByName(name)
        source?.let { dict ->
            sourceFields.clear()
            sourceFields.addAll(dict.availableFields.map { it.toString() })
        }
    }

    fun onModelSelected(name: String) {
        modelName.selected = name
        isModelSelected = true
        AnkiDroidHelper.getModelFields(name)?.let {
            modelFields.clear()
            modelFields.addAll(it)
        }
    }

    fun onFieldMappingChange(modelField: String, sourceField: String) {
        fieldMapping[modelField] = sourceField
    }

    fun toSchema() = Schema(
        id = schemaId,
        name = schemaName.text,
        source = SourceRegistry.getByName(sourceName.selected)?.id ?: "unknown",
        deck = deckName.selected,
        model = modelName.selected,
        fieldMapping = fieldMapping.toMap()
    )
}