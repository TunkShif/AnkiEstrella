package one.tunkshif.ankiestrella.data.source

import androidx.compose.runtime.Composable
import one.tunkshif.ankiestrella.api.DictSource
import one.tunkshif.ankiestrella.data.model.Definition
import one.tunkshif.ankiestrella.data.model.Field
import one.tunkshif.ankiestrella.data.model.Field.*
import one.tunkshif.ankiestrella.data.model.Word

object YoudaoCollins : DictSource {
    override val id = "youdao-collins"
    override val name: String = "Youdao Collins"
    override val availableFields: List<Field> =
        listOf(WORD, PHONETICS, POS, SENSE, SENSE_TRANSLATION, EXAMPLE, EXAMPLE_TRANSLATION)

    override suspend fun query(text: String): Word {
        TODO("Not yet implemented")
    }

    @Composable
    override fun Item(definition: Definition, onClick: () -> Unit) {
        TODO("Not yet implemented")
    }
}