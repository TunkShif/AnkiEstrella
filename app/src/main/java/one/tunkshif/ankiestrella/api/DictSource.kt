package one.tunkshif.ankiestrella.api

import androidx.compose.runtime.Composable
import one.tunkshif.ankiestrella.data.model.Field
import one.tunkshif.ankiestrella.data.model.Word
import org.koin.core.component.KoinComponent

interface DictSource : KoinComponent {
    val id: String
    val name: String
    val availableFields: List<Field>
    suspend fun query(text: String): Word?

    @Composable
    fun Item(item: Word, onClick: () -> Unit)
}