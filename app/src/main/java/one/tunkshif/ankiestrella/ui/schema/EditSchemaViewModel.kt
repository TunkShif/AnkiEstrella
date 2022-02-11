package one.tunkshif.ankiestrella.ui.schema

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import one.tunkshif.ankiestrella.data.SourceRegistry
import one.tunkshif.ankiestrella.data.model.Field
import one.tunkshif.ankiestrella.data.model.Schema
import one.tunkshif.ankiestrella.util.AnkiDroidHelper
import one.tunkshif.ankiestrella.util.LOG_TAG

class EditSchemaViewModel : ViewModel() {
    var schema by mutableStateOf(Schema())
        private set

    val sourceFields = mutableStateListOf<Field>()
    val modelFields = mutableStateListOf<String>()
    val fieldMapping = mutableStateMapOf<String, String>()

    private var sourceId = ""

    val decks = AnkiDroidHelper.getAllDecks()
    val models = AnkiDroidHelper.getAllModels()
    val sources = SourceRegistry.all().map { it.name }

    fun onSchemaNameChange(name: String) {
        schema = schema.copy(name = name)
    }

    fun onDeckNameChange(name: String) {
        schema = schema.copy(deck = name)
    }

    fun onSourceNameChange(name: String) {
        schema = schema.copy(source = name)
        // TODO: Error handling
        val source = SourceRegistry.getByName(name)
        sourceId = source?.id ?: sourceId
        sourceFields.clear()
        source?.availableFields?.let { sourceFields.addAll(it) }
    }

    fun onModelNameChange(name: String) {
        schema = schema.copy(model = name)
        // TODO: Error handling
        modelFields.clear()
        AnkiDroidHelper.getModelFields(name)?.let { modelFields.addAll(it) }
        fieldMapping.apply {
            clear()
            putAll(modelFields.associateWith { "" })
        }
    }

    fun onFieldMappingChange(modelField: String, sourceField: String) {
        fieldMapping[modelField] = sourceField
    }

    fun onSave() {
        Log.d(LOG_TAG, fieldMapping.toMap().toString())
    }
}
