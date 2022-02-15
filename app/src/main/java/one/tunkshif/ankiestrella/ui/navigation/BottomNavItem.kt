package one.tunkshif.ankiestrella.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import one.tunkshif.ankiestrella.R

sealed class BottomNavItem(
    val route: String,
    val name: String,
    @DrawableRes val icon: Int
) {
    object Home : BottomNavItem("home_screen", "Home", R.drawable.ic_home_solid)
    object Search : BottomNavItem("search_screen", "Search", R.drawable.ic_search_solid)
    object Favorite : BottomNavItem("favorite_screen", "Favorite", R.drawable.ic_heart_solid)
    object Settings : BottomNavItem("settings_screen", "Settings", R.drawable.ic_cog_solid)

    companion object {
        fun all() = listOf(Home, Search, Favorite, Settings)
    }
}