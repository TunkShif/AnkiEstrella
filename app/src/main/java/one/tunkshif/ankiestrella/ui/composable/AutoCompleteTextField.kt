package one.tunkshif.ankiestrella.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.toSize

@Composable
fun AutoCompleteTextField(
    label: String,
    value: String,
    items: List<String>,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var expanded by remember { mutableStateOf(false) }

    // TODO: expanded icon
    // TODO: disable new line

    Box(
        modifier = modifier.onGloballyPositioned {
            textFieldSize = it.size.toSize()
        },
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            maxLines = 1,
            label = { Text(text = label) },
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Expand")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(
                    with(LocalDensity.current) {
                        textFieldSize.width.toDp()
                    }
                )
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(onClick = {
                    onValueChange(items[index])
                    expanded = false
                }) {
                    Text(text = item)
                }
            }
        }
    }
}