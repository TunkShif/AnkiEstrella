package one.tunkshif.ankiestrella.ui.navigation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BottomScaffold(
    navController: NavController,
    topBar: @Composable () -> Unit,
    floatingActionButton: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = topBar,
        bottomBar = { BottomNavBar(navController) },
        floatingActionButton = floatingActionButton
    ) {
        content()
    }
}
