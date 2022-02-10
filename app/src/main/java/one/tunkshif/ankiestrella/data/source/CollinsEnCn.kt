package one.tunkshif.ankiestrella.data.source

import androidx.compose.runtime.Composable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import one.tunkshif.ankiestrella.AnkiEstrella
import one.tunkshif.ankiestrella.api.DictSource
import one.tunkshif.ankiestrella.data.DictDbHelper
import one.tunkshif.ankiestrella.data.model.Definition
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
        return Word(
            query = text,
            word = result[0],
            phonetics = result[1],
            audioUrl = "TODO",
            definitions = Gson().fromJson(result[2], object : TypeToken<List<Definition>>() {}.type)
        )
    }

    @Composable
    override fun Item(item: Word, onClick: () -> Unit) {
        TODO("Not yet implemented")
    }
}