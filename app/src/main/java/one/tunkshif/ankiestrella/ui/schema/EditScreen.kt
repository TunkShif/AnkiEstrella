package one.tunkshif.ankiestrella.ui.schema

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import one.tunkshif.ankiestrella.R
import one.tunkshif.ankiestrella.ui.composable.AutoCompleteTextField
import one.tunkshif.ankiestrella.ui.theme.*
import org.koin.androidx.compose.inject

@Composable
fun EditScreen(
    viewModel: EditSchemaViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Edit Schema") },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_left_outline),
                            contentDescription = "back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_dots_vertical_outline),
                            contentDescription = "menu"
                        )
                    }
                })
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = AnkiBlue200,
                contentColor = White.copy(alpha = 0.95f),
                onClick = viewModel::onSave
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check_outline),
                    contentDescription = "finish"
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimension.small, Dimension.extraSmall, Dimension.small, Dimension.none)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(Dimension.small),
                modifier = Modifier.fillMaxWidth()
            ) {
                SchemaEditSection(viewModel = viewModel)
                if (viewModel.schema.model.isNotEmpty()) {
                    FieldMappingSection(viewModel = viewModel)
                }
            }
        }
    }
}

// TODO: TextField keyboard behavior

@Composable
fun SchemaEditSection(viewModel: EditSchemaViewModel) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimension.tiny),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = viewModel.schema.name,
            onValueChange = viewModel::onSchemaNameChange,
            shape = Rounded.large,
            label = { Text(text = "Name") },
            placeholder = { Text(text = "Schema name") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_document_solid),
                    contentDescription = "schema name"
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        AutoCompleteTextField(
            value = viewModel.schema.deck,
            onValueChange = viewModel::onDeckNameChange,
            items = viewModel.decks,
            shape = Rounded.large,
            label = { Text(text = "Deck") },
            placeholder = { Text(text = "Deck name") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_collection_solid),
                    contentDescription = "deck name"
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        AutoCompleteTextField(
            value = viewModel.schema.source,
            onValueChange = viewModel::onSourceNameChange,
            items = viewModel.sources,
            shape = Rounded.large,
            label = { Text(text = "Dict") },
            placeholder = { Text(text = "Dict name") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_book_open_solid),
                    contentDescription = "dict name"
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        AutoCompleteTextField(
            value = viewModel.schema.model,
            onValueChange = viewModel::onModelNameChange,
            items = viewModel.models,
            shape = Rounded.large,
            label = { Text(text = "Model") },
            placeholder = { Text(text = "Model Name") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_clipboard_solid),
                    contentDescription = "model name"
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun FieldMappingSection(
    viewModel: EditSchemaViewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimension.small)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Dimension.tiny),
            modifier = Modifier.fillMaxWidth()
        ) {
            val style =
                TextStyle(fontSize = 18.sp, fontFamily = fontOutfit, fontWeight = FontWeight.Medium)

            Text(text = "FIELD", style = style, modifier = Modifier.weight(3f))
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "SOURCE", style = style, modifier = Modifier.weight(3f))
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(Dimension.extraSmall)
        ) {
            viewModel.modelFields.forEach { modelField ->
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = modelField,
                            fontSize = 16.sp,
                            fontFamily = fontOutfit,
                            modifier = Modifier.weight(2f)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_narrow_right_outline),
                            contentDescription = "right arrow",
                            modifier = Modifier.weight(1f)
                        )
                        AutoCompleteTextField(
                            value = viewModel.fieldMapping[modelField] ?: "",
                            onValueChange = { viewModel.onFieldMappingChange(modelField, it) },
                            items = viewModel.sourceFields.map { it.toString() },
                            shape = Rounded.large,
                            modifier = Modifier.weight(3f)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun EditScreenPreview() {
    val editSchemaViewModel: EditSchemaViewModel by inject()
    AnkiEstrellaTheme {
        EditScreen(editSchemaViewModel)
    }
}
