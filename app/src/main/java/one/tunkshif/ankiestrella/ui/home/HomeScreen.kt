package one.tunkshif.ankiestrella.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import one.tunkshif.ankiestrella.R
import one.tunkshif.ankiestrella.ui.theme.*

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
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
        },
        floatingActionButton = {
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
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimension.small, Dimension.extraSmall, Dimension.small, Dimension.none)
        ) {
            Column {
                OverviewSection()
                Spacer(modifier = Modifier.height(Dimension.small))
                SchemaSection()
            }
        }
    }
}

@Composable
fun OverviewSection() {
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
                count = 23,
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ }
            )
            Spacer(modifier = Modifier.width(Dimension.extraSmall))
            OverviewCard(
                iconId = R.drawable.ic_book_open_solid,
                iconDescription = "dict",
                name = "DICT",
                count = 7,
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ }
            )
        }
    }
}

@Composable
fun SchemaSection() {
    Column {
        Text(text = "Your Schemas", color = Night, fontSize = 22.sp, fontFamily = fontOutfit)
        Spacer(modifier = Modifier.height(Dimension.small))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(Dimension.tiny)
        ) {
            items(20) {
                SchemaItem(
                    name = "Schema Name",
                    deckName = "English::Vocabulary",
                    onButtonClick = { /*TODO*/ }
                )
            }
        }
    }
}
