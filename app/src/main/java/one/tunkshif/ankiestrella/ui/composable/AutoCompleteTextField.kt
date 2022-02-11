package one.tunkshif.ankiestrella.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.toSize
import one.tunkshif.ankiestrella.R

@Composable
fun AutoCompleteTextField(
    value: String,
    onValueChange: (String) -> Unit,
    items: List<String>,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    shape: Shape = MaterialTheme.shapes.small,
) {

    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.onGloballyPositioned { textFieldSize = it.size.toSize() },
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = false,
            singleLine = true,
            label = label,
            shape = shape,
            textStyle = textStyle,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    if (expanded) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chevron_up_outline),
                            contentDescription = "collapse"
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chevron_down_outline),
                            contentDescription = "expand"
                        )
                    }
                }
            },
            colors = textFieldColors(),
            modifier = modifier
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(
                    onClick = {
                        onValueChange(items[index])
                        expanded = false
                    }) {
                    Text(text = item)
                }
            }
        }
    }
}

/**
 * we are using `enabled = false` to make [OutlinedTextField] cannot be clicked or focused, here we
 * are overriding the disabled color variant to match the normal one.
 */
@Composable
private fun textFieldColors(): TextFieldColors {
    return TextFieldDefaults.outlinedTextFieldColors(
        disabledTextColor = LocalContentColor.current.copy(LocalContentAlpha.current),
        disabledLabelColor = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium),
        disabledLeadingIconColor = MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.IconOpacity),
        disabledPlaceholderColor = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium),
        disabledTrailingIconColor = MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.IconOpacity),
    )
}