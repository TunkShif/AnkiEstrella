package one.tunkshif.ankiestrella.ui.composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class TextFieldState(
    private val validator: (String) -> Boolean = { it.isNotEmpty() },
    private val errorFor: (String) -> String = { "Cannot be empty." }
) {
    var text by mutableStateOf("")
    var isFocusedEver by mutableStateOf(false)
    var isFocused by mutableStateOf(false)
    private var showError by mutableStateOf(false)

    val isValid
        get() = validator(text)

    val shouldShowError
        get() = !isValid && showError

    fun onFocusChange(focused: Boolean) {
        isFocused = focused
        if (focused) isFocusedEver = true
    }

    fun enableShowError() {
        if (isFocusedEver) showError = true
    }

    fun getError() = if (shouldShowError) errorFor(text) else null
}