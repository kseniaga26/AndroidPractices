package ru.kseniaga.androidpractices.ui.theme.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import ru.kseniaga.androidpractices.screens.TitleListScreen
import ru.kseniaga.androidpractices.screens.HomeScreen
import ru.kseniaga.androidpractices.screens.TitleDetailScreen
import org.koin.androidx.compose.koinViewModel
import ru.kseniaga.androidpractices.components.TitleViewModel
import ru.kseniaga.androidpractices.presentation.model.TitleUiModel
import ru.kseniaga.androidpractices.screens.FavoritesScreen
import ru.kseniaga.androidpractices.screens.PersonalAccountScreen
import ru.kseniaga.androidpractices.screens.SettingsScreen
import ru.kseniaga.androidpractices.ui.theme.Pink40
import ru.kseniaga.androidpractices.ui.theme.PurpleGrey80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    var currentDestination by remember { mutableStateOf("title") }
    val viewModel: TitleViewModel = koinViewModel<TitleViewModel>()
    val state = viewModel.viewState

    viewModel.viewState.error?.let {
        Text(text = it)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PurpleGrey80,
                    titleContentColor = Pink40,
                ),
                title = {
                    Text("Фильмы с Кинопоиска")
                },
                actions = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "TitlesList",
            modifier = modifier.padding(innerPadding) // Применяем отступы от TopAppBar
        ) {
            composable("TitlesList") {
                currentDestination = "TitlesList"
                TitleListScreen(viewModel) { movieId ->
                    navController.navigate("movie_detail/$movieId")
                }
            }
            composable("movie_detail/{movieId}") { backStackEntry ->
                currentDestination = "movie_detail"
                val id = backStackEntry.arguments?.getString("movieId")?.toLong() ?: 0L

                val movie: TitleUiModel? = state.items.find { it.id == id }

                if (movie != null) {
                    TitleDetailScreen(movie)
                }
            }
            composable("Settings") { SettingsScreen(viewModel) }
            composable("PersonalAccount") { PersonalAccountScreen() }
            composable("FavoriteList") { FavoritesScreen(navController) }
        }
    }
}


