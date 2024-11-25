package ru.kseniaga.androidpractices.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import ru.kseniaga.androidpractices.components.TitleViewModel
import ru.kseniaga.androidpractices.presentation.model.TitleUiModel
import ru.kseniaga.androidpractices.ui.theme.screens.LoadingScreen


@Composable
fun TitleListScreen(viewModel: TitleViewModel, onMovieClick: (Long) -> Unit) {
    val state = viewModel.viewState
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        state.error?.let {
            Text(text = it)
            Spacer(modifier = Modifier.height(18.dp))
            Button(onClick = viewModel::loadFilms) {
                Text("Try Again")
            }
        }

        LazyColumn(
            Modifier.fillMaxSize(),
        ) {
            items(state.items) {
                ConstructorItem(title = it, onMovieClick)
            }
        }
    }

    if (state.loading) {
        LoadingScreen()
    }
}


@Composable
private fun ConstructorItem(title: TitleUiModel, onMovieClick: (Long) -> Unit) {
    ListItem(modifier = Modifier
        .clickable { onMovieClick(title.id) }
        .padding(8.dp)
        .shadow(10.dp)
        .clip(
            RoundedCornerShape(10.dp)
        ), headlineContent = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(title.posterUrl),
                contentDescription = null,
                modifier = Modifier.size(170.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(title.name, style = MaterialTheme.typography.titleLarge)
                Text(
                    title.rating.toString() + "\n "+ title.genre,
                    style = MaterialTheme.typography.bodyLarge
                )

            }
        }
    })
}



