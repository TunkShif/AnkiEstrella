package one.tunkshif.ankiestrella.data.source

import androidx.compose.runtime.Composable
import one.tunkshif.ankiestrella.api.DictSource
import one.tunkshif.ankiestrella.data.model.Field
import one.tunkshif.ankiestrella.data.model.Field.*
import one.tunkshif.ankiestrella.data.model.Word
import one.tunkshif.ankiestrella.data.service.SpanishDictService
import org.koin.core.component.inject

object SpanishDict : DictSource {
    override val id: String = "spanishdict"
    override val name: String = "Spanish Dict"
    override val availableFields: List<Field> =
        listOf(WORD, AUDIO, POS, SENSE, SENSE_TRANSLATION, EXAMPLE, EXAMPLE_TRANSLATION)

    private val spanishDictService: SpanishDictService by inject()

    override suspend fun query(text: String): Word? {
        // TODO
        return spanishDictService.getWordQuery(text).result
    }

    @Composable
    override fun Item(item: Word, onClick: () -> Unit) {
        TODO("Not yet implemented")
    }

}