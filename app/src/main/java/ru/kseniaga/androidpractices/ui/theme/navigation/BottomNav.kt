
package ru.kseniaga.androidpractices.ui.theme.navigation

import android.util.Log
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import ru.kseniaga.androidpractices.datastore.DataStoreManager
import ru.kseniaga.androidpractices.ui.theme.Pink40
import ru.kseniaga.androidpractices.ui.theme.PurpleGrey80
import ru.kseniaga.androidpractices.utils.LocalUtils.isFilter

@Composable
fun BottomNav(navController: NavController) {
    val bottomNavController = remember { mutableStateOf(0) }

    BottomNavigation (
        backgroundColor = PurpleGrey80,
        contentColor = Pink40
    ) {
        val navItems = listOf(
            "TitlesList" to Icons.Filled.List,
            "Settings" to Icons.Filled.Settings,
            "FavoriteList" to Icons.Filled.Favorite,
            "PersonalAccount" to Icons.Filled.AccountCircle,
        )
        navItems.forEachIndexed { index, pair ->
            val (item, icon) = pair
            BottomNavigationItem(
                icon = {
                    BadgedBox(
                        badge=  {
                            if(isFilter.value && icon.name == "Filled.List"){
                                Badge()
                            }
                        }
                    ) {
                        Icon(icon, contentDescription = null)
                    }
                },
                selected = bottomNavController.value == index,
                onClick = {
                    bottomNavController.value = index
                    navController.navigate(item) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

