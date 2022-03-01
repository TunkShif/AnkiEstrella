package one.tunkshif.ankiestrella.ui.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import one.tunkshif.ankiestrella.ui.navtype.schemaNavType
import one.tunkshif.ankiestrella.ui.theme.Rounded
import one.tunkshif.ankiestrella.ui.theme.fontOutfit

@Composable
fun Chip(
    text: String,
    modifier: Modifier = Modifier,
    background: Color = MaterialTheme.colors.primary,
    textStyle: TextStyle = TextStyle.Default.copy(
        fontSize = 18.sp,
        color = MaterialTheme.colors.onPrimary
    )
) {
    Surface(
        color = background,
        shape = Rounded.full,
        modifier = modifier
    ) {
        Text(
            text = text,
            style = textStyle,
            textAlign = TextAlign.Center,
            fontFamily = fontOutfit,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }
}