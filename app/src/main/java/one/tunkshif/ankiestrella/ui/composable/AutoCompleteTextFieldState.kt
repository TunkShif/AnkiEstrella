package one.tunkshif.ankiestrella.ui.composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class AutoCompleteTextFieldState {
    var selected by mutableStateOf("")
    val items = mutableListOf<String>()

    fun onItemsChange(items: List<String>) {
        this.items.clear()
        this.items.addAll(items)
    }
}