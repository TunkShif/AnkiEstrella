package one.tunkshif.ankiestrella.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import one.tunkshif.ankiestrella.ui.home.HomeScreen
import one.tunkshif.ankiestrella.ui.home.HomeViewModel
import one.tunkshif.ankiestrella.ui.theme.AnkiEstrellaTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnkiEstrellaTheme {
                HomeScreen(viewModel = homeViewModel)
            }
        }
    }
}