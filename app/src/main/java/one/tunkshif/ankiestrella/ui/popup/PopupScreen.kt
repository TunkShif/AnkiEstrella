package one.tunkshif.ankiestrella.ui.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import one.tunkshif.ankiestrella.R
import one.tunkshif.ankiestrella.ui.theme.*

@Composable
fun PopupScreen() {
    Scaffold(
        topBar = {
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
        },
        floatingActionButton = {
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
    ) {
        Box(
            modifier = Modifier
                .background(AnkiBlue100)
                .fillMaxSize()
        ) {
            HeaderSection()
        }
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
        Spacer(modifier = Modifier.height(Dimension.medium))
        MainSection()
    }
}

@Composable
fun ActionButton(
    name: String,
    iconId: Int,
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
fun MainSection() {
    Box(
        modifier = Modifier
            .padding(horizontal = Dimension.extraSmall)
            .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
            .background(color = White)
            .fillMaxSize()
            .padding(Dimension.extraSmall)
    ) {
        // TODO: Tab Layout
    }
}

@Preview
@Composable
fun PopupScreenPreview() {
    AnkiEstrellaTheme {
        PopupScreen()
    }
}