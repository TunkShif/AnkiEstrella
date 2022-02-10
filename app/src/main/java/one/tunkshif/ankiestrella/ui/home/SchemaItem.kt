package one.tunkshif.ankiestrella.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import one.tunkshif.ankiestrella.R
import one.tunkshif.ankiestrella.ui.theme.*

@Composable
fun SchemaItem(
    name: String,
    deckName: String,
    onButtonClick: () -> Unit
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = name,
                    color = Gray600,
                    fontSize = 18.sp,
                    fontFamily = fontOutfit,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(Dimension.tiny))
                Text(
                    text = deckName,
                    color = Gray300,
                    fontSize = 16.sp,
                    fontFamily = fontOutfit
                )
            }
            IconButton(onClick = { onButtonClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_pencil_solid),
                    contentDescription = "edit",
                    tint = Night,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(Dimension.tiny))
        Divider(color = Gray50, modifier = Modifier.height(2.dp))
    }
}