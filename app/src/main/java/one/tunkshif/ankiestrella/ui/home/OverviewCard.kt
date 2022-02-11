package one.tunkshif.ankiestrella.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import one.tunkshif.ankiestrella.ui.theme.*

@Composable
fun OverviewCard(
    iconId: Int,
    iconDescription: String,
    onClick: () -> Unit,
    name: String,
    count: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .shadow(elevation = Dimension.tiny, shape = Rounded.large)
            .clip(Rounded.large)
            .background(AnkiBlue100)
            .clickable { onClick() }
            .padding(Dimension.small)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(iconId),
                tint = White,
                contentDescription = iconDescription,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(Dimension.small))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = name,
                    color = Gray50, fontSize = 20.sp, fontFamily = fontOutfit
                )
                Spacer(modifier = Modifier.height(Dimension.extraSmall))
                Row {
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    color = White,
                                    fontSize = 16.sp,
                                    fontFamily = fontOutfit,
                                    fontWeight = FontWeight.Medium
                                )
                            ) { append(count.toString()) }
                            withStyle(
                                SpanStyle(
                                    color = White,
                                    fontSize = 16.sp,
                                    fontFamily = fontOutfit
                                )
                            ) { append(" available") }
                        }
                    )
                }
            }
        }
    }
}