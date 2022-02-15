package one.tunkshif.ankiestrella.ui.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import one.tunkshif.ankiestrella.data.repository.SchemaRepository

class EditorViewModel(
    private val schemaRepository: SchemaRepository
) : ViewModel() {
    val formState = EditorFormState()

    suspend fun hasConflict(): Boolean {
        var hasConflict = false
        val name = formState.schemaName.text
        val schema = viewModelScope.async {
            schemaRepository.getOne(name)
        }
        schema.await()?.let { hasConflict = true }
        formState.hasConflict = hasConflict
        return hasConflict
    }

    fun onAlertCancel() {
        formState.hasConflict = false
    }

    fun onAlertConfirm() {
        update()
        formState.hasConflict = false
    }

    fun save() {
        viewModelScope.launch {
            val schema = formState.toSchema()
            schemaRepository.save(schema)
        }
    }

    fun update() {
        viewModelScope.launch {
            var schema = formState.toSchema()
            if (formState.schemaId == 0) {
                schemaRepository.getOne(name = formState.schemaName.text)?.let {
                    schema = schema.copy(id = it.id)
                }
            }
            schemaRepository.update(schema)
        }
    }
}