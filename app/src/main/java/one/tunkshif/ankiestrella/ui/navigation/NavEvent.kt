package one.tunkshif.ankiestrella.ui.navigation

import com.ramcosta.composedestinations.spec.Direction

data class NavEvent(
    private val destination: Direction
) {
    var isHandled = false
        private set

    fun getIfNotHandled() =
        if (isHandled) {
            null
        } else {
            isHandled = true
            destination
        }

    fun peek() = destination
}