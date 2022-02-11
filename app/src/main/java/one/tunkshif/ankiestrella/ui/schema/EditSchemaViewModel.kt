package one.tunkshif.ankiestrella.ui.schema

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import one.tunkshif.ankiestrella.data.SourceRegistry
import one.tunkshif.ankiestrella.data.model.Schema
import one.tunkshif.ankiestrella.util.AnkiDroidHelper

class EditSchemaViewModel : ViewModel() {
    var schema by mutableStateOf(Schema())
        private set

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
    }

    fun onModelNameChange(name: String) {
        schema = schema.copy(model = name)
    }
}
