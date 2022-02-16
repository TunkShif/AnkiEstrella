package one.tunkshif.ankiestrella.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import one.tunkshif.ankiestrella.R

sealed class BottomNavItem(
    val route: String,
    val name: String,
    @DrawableRes val icon: Int
) {
    object Home : BottomNavItem("home", "Home", R.drawable.ic_home_solid)
    object Search : BottomNavItem("search", "Search", R.drawable.ic_search_solid)
    object Favorite : BottomNavItem("favorite", "Favorite", R.drawable.ic_heart_solid)
    object Settings : BottomNavItem("settings", "Settings", R.drawable.ic_cog_solid)

    companion object {
        fun all() = listOf(Home, Search, Favorite, Settings)
    }
}