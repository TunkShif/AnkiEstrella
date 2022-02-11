package one.tunkshif.ankiestrella.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import one.tunkshif.ankiestrella.ui.theme.AnkiEstrellaTheme

class PopupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnkiEstrellaTheme {
            }
        }
    }
}