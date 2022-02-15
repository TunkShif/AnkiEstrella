package one.tunkshif.ankiestrella.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import one.tunkshif.ankiestrella.R
import one.tunkshif.ankiestrella.data.model.Schema
import one.tunkshif.ankiestrella.ui.navigation.BottomScaffold
import one.tunkshif.ankiestrella.ui.theme.*
import org.koin.androidx.compose.inject

@Destination(start = true)
@Composable
fun HomeScreen(
    navController: NavController
) {
    val viewModel: HomeViewModel by inject()

    val lifecycleOwner = LocalLifecycleOwner.current
    val uiStateLive = remember(viewModel.uiState, lifecycleOwner) {
        viewModel.uiState.flowWithLifecycle(lifecycleOwner.lifecycle)
    }

    val uiState by uiStateLive.collectAsState(initial = HomeUiState.Loaded())

    BottomScaffold(
        navController = navController,
        topBar = { HomeScreenTopAppBar() },
        floatingActionButton = { HomeScreenFloatingActionButton() }
    ) {
        // smart cast cannot be performed on delegated value
        when (val state = uiState) {
            is HomeUiState.Loaded -> HomeScreenContent(
                schemas = state.schemas,
                deckCount = state.deckCount,
                dictCount = state.dictCount
            )
            is HomeUiState.Empty -> Dummy()
            is HomeUiState.Error -> Dummy()
        }
    }
}

@Composable
fun HomeScreenTopAppBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name)) },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_dots_vertical_outline),
                    tint = IconTint,
                    contentDescription = "menu button"
                )
            }
        },
    )
}

@Composable
fun HomeScreenFloatingActionButton() {
    FloatingActionButton(
        backgroundColor = AnkiBlue200,
        contentColor = White.copy(alpha = 0.95f),
        onClick = { /*TODO*/ }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_plus_outline),
            contentDescription = "add"
        )
    }
}

@Composable
fun HomeScreenContent(
    schemas: List<Schema>,
    deckCount: Int,
    dictCount: Int
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimension.small, Dimension.extraSmall, Dimension.small, Dimension.none)
    ) {
        Column {
            OverviewSection(
                deckCount = deckCount,
                dictCount = dictCount
            )
            Spacer(modifier = Modifier.height(Dimension.small))
            SchemaSection(
                schemas = schemas
            )
        }
    }
}

@Composable
fun Dummy() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimension.small, Dimension.extraSmall, Dimension.small, Dimension.none)
    ) {
        Text(text = "Not Implemented Yet")
    }
}

@Composable
fun OverviewSection(
    deckCount: Int,
    dictCount: Int
) {
    Column {
        Text(text = "Overview", color = Night, fontSize = 22.sp, fontFamily = fontOutfit)
        Spacer(modifier = Modifier.height(Dimension.small))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            OverviewCard(
                iconId = R.drawable.ic_collection_solid,
                iconDescription = "decks",
                name = "DECK",
                count = deckCount,
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ }
            )
            Spacer(modifier = Modifier.width(Dimension.extraSmall))
            OverviewCard(
                iconId = R.drawable.ic_book_open_solid,
                iconDescription = "dict",
                name = "DICT",
                count = dictCount,
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ }
            )
        }
    }
}

@Composable
fun SchemaSection(
    schemas: List<Schema>
) {
    Column {
        Text(text = "Your Schemas", color = Night, fontSize = 22.sp, fontFamily = fontOutfit)
        Spacer(modifier = Modifier.height(Dimension.small))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(Dimension.tiny)
        ) {
            items(schemas) {
                SchemaItem(
                    name = it.name,
                    deckName = it.deck,
                    onButtonClick = { /* TODO */ }
                )
            }
        }
    }
}
