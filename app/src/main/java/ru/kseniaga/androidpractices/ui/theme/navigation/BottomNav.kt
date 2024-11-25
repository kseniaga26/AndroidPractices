
package ru.kseniaga.androidpractices.ui.theme.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.navigation.NavController
import ru.kseniaga.androidpractices.ui.theme.Pink40
import ru.kseniaga.androidpractices.ui.theme.PurpleGrey80

@Composable
fun BottomNav(navController: NavController) {
    val bottomNavController = remember { mutableStateOf(0) }

    BottomNavigation (
        backgroundColor = PurpleGrey80,
        contentColor = Pink40
    ) {
        val navItems = listOf(
            "Home" to Icons.Filled.Home,
            "Notification" to Icons.Filled.Notifications,
            "TitlesList" to Icons.Filled.List,
            "PersonalAccount" to Icons.Filled.AccountCircle
        )
        navItems.forEachIndexed { index, pair ->
            val (item, icon) = pair
            BottomNavigationItem(
                icon = { Icon(icon, contentDescription = null) },
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