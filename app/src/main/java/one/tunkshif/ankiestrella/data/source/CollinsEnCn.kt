package one.tunkshif.ankiestrella.data.source

import androidx.compose.runtime.Composable
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import one.tunkshif.ankiestrella.AnkiEstrella
import one.tunkshif.ankiestrella.api.DictSource
import one.tunkshif.ankiestrella.data.DictDbHelper
import one.tunkshif.ankiestrella.data.model.Field
import one.tunkshif.ankiestrella.data.model.Field.*
import one.tunkshif.ankiestrella.data.model.Word

object CollinsEnCn : DictSource {
    override val id: String = "collins-en-cn"
    override val name: String = "Collins EN-CN"
    override val availableFields: List<Field> =
        listOf(WORD, PHONETICS, AUDIO, POS, SENSE, SENSE_TRANSLATION, EXAMPLE, EXAMPLE_TRANSLATION)

    override suspend fun query(text: String): Word? {
        val db = DictDbHelper(AnkiEstrella.context, id)
        val result = db.query(text)
        val mapper = ObjectMapper().registerKotlinModule()
        return Word(
            query = text,
            word = result[0],
            phonetics = result[1],
            audioUrl = "TODO",
            definitions = mapper.readValue(result[2])
        )
    }

    @Composable
    override fun Item(item: Word, onClick: () -> Unit) {
        TODO("Not yet implemented")
    }
}