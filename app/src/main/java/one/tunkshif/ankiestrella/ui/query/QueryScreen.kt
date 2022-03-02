package one.tunkshif.ankiestrella.ui.query

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.flowWithLifecycle
import one.tunkshif.ankiestrella.R
import one.tunkshif.ankiestrella.data.model.Definition
import one.tunkshif.ankiestrella.data.source.YoudaoCollins
import one.tunkshif.ankiestrella.ui.theme.*
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf

@Composable
fun QueryScreen() {
    val query = "nieve"
    val viewModel: QueryViewModel by viewModel { parametersOf(query) }

    val lifecycleOwner = LocalLifecycleOwner.current
    val uiStateLive = remember(viewModel.uiState, lifecycleOwner) {
        viewModel.uiState.flowWithLifecycle(lifecycleOwner.lifecycle)
    }

    val uiState by uiStateLive.collectAsState(initial = QueryUiState.NoSchemaError)
    
    Scaffold(
        topBar = { QueryScreenTopBar() },
        floatingActionButton = { QueryScreenFloatingActionButton() }
    ) {
        Box(
            modifier = Modifier
                .background(AnkiBlue100)
                .fillMaxSize()
        ) {
            Column {
                HeaderSection()
                Spacer(modifier = Modifier.height(Dimension.medium))
                MainSection {
                    when (val state = uiState) {
                        is QueryUiState.NoSchemaError -> Text(text = "No schema ")
                        is QueryUiState.StartLoading -> {
                            TabLayout(
                                tabItems = viewModel.schemas.map { it.name }
                            ) {
                                Text(text = "Loading")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QueryScreenTopBar() {
    TopAppBar(
        title = {},
        elevation = Dimension.none,
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_left_outline),
                    tint = White,
                    contentDescription = "back"
                )
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_dots_vertical_outline),
                    tint = IconTint,
                    contentDescription = "menu"
                )
            }
        }
    )
}

@Composable
fun QueryScreenFloatingActionButton() {
    // TODO: hide fab when scrolling
    FloatingActionButton(
        backgroundColor = AnkiBlue200,
        contentColor = White.copy(alpha = 0.95f),
        onClick = { /*TODO*/ }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_check_outline),
            contentDescription = "finish"
        )
    }
}

@Composable
fun HeaderSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = "test", color = White, fontSize = 28.sp, fontFamily = fontRubik)
        Spacer(modifier = Modifier.height(Dimension.small))
        Row(
            horizontalArrangement = Arrangement.spacedBy(36.dp)
        ) {
            ActionButton(
                name = "play audio",
                iconId = R.drawable.ic_volume_up_outline,
                onClick = { /*TODO*/ }
            )
            ActionButton(
                name = "add tag",
                iconId = R.drawable.ic_tag_outline,
                onClick = { /*TODO*/ }
            )
            ActionButton(
                name = "add note",
                iconId = R.drawable.ic_pencil_alt_outline,
                onClick = { /*TODO*/ }
            )
        }
    }
}

@Composable
fun ActionButton(
    name: String,
    @DrawableRes iconId: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(Rounded.medium)
            .background(color = White.copy(0.65f))
            .clickable { onClick() }
            .padding(10.dp)
    ) {
        Icon(
            painter = painterResource(id = iconId),
            tint = AnkiBlue100.copy(alpha = 0.95f),
            contentDescription = name
        )
    }
}

@Composable
fun MainSection(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = Dimension.extraSmall)
            .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
            .background(color = White)
            .fillMaxSize()
    ) {
        content()
    }
}

@Composable
fun DefinitionList(definitions: List<Definition>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(definitions) { definition ->
            YoudaoCollins.Item(definition = definition, onClick = {})
            Divider(color = Gray50, modifier = Modifier.padding(top = 6.dp))
        }
    }
}

@Preview
@Composable
fun PopupScreenPreview() {
    AnkiEstrellaTheme {
        QueryScreen()
    }
}