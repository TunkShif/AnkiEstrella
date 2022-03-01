package one.tunkshif.ankiestrella.data.source

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import one.tunkshif.ankiestrella.api.DictSource
import one.tunkshif.ankiestrella.data.model.Definition
import one.tunkshif.ankiestrella.data.model.Field
import one.tunkshif.ankiestrella.data.model.Field.*
import one.tunkshif.ankiestrella.data.model.Word
import one.tunkshif.ankiestrella.data.service.SpanishDictService
import one.tunkshif.ankiestrella.ui.composable.Chip
import one.tunkshif.ankiestrella.ui.theme.Dimension
import one.tunkshif.ankiestrella.ui.theme.Gray300
import one.tunkshif.ankiestrella.ui.theme.Rounded
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
    override fun Item(definition: Definition, onClick: () -> Unit) {
        val example = definition.examples.firstOrNull()
        Box(modifier = Modifier
            .clip(Rounded.small)
            .clickable { onClick() }
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .fillMaxWidth()
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Chip(text = definition.pos!!)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = definition.sense, fontSize = 18.sp)
                }
                example?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(start = Dimension.extraSmall)
                    ) {
                        Text(text = it.example)
                        Text(text = it.exampleTranslation!!, color = Gray300)
                    }
                }
            }
        }
    }
}